package com.ace.vishal.helpinghands;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Hp on 1/11/2017.
 */
public class deaffragment extends android.support.v4.app.Fragment implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private Button buttonSpeak;
    private EditText editText;
    private EditText filename;
    String content;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main2, null);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tts = new TextToSpeech(getActivity(),this);
        buttonSpeak = (Button) getView().findViewById(R.id.button1);
        editText = (EditText) getView().findViewById(R.id.editText1);
        filename=(EditText)getView().findViewById(R.id.filespeak);
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }

        });
    }
    @Override
    public void onDestroy() {
// Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                buttonSpeak.setEnabled(true);
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut() {
        String text = editText.getText().toString();
        try{
            FileInputStream inStream=getContext().openFileInput(filename.getText().toString());
            InputStreamReader inputStreamReader=new InputStreamReader(inStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder finalString=new StringBuilder();
            String oneLine;
            while((oneLine=bufferedReader.readLine())!=null){
                finalString.append(oneLine);
            }
            bufferedReader.close();
            inStream.close();
            inputStreamReader.close();
            content=finalString.toString();
        }catch (IOException e) {
            e.printStackTrace();
        }
        editText.setText(content);
        tts.speak(content, TextToSpeech.QUEUE_FLUSH, null);
    }

}
