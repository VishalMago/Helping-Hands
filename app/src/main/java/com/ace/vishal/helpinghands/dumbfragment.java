package com.ace.vishal.helpinghands;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

/**
 * Created by Hp on 1/11/2017.
 */
public class dumbfragment extends android.support.v4.app.Fragment implements View.OnClickListener{
    Button save;
    private final static String STORETEXT="storetext.txt";
    private EditText input;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.dumb, null);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        save=(Button)getView().findViewById(R.id.save);
        input=(EditText)getView().findViewById(R.id.editText1);
        save.setOnClickListener(this);
    }
    public void onClick(View v){
        if(v==save) {
            LayoutInflater li = LayoutInflater.from(getContext());
            View promptsView = li.inflate(R.layout.filename, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.filename);
            // set dialog message
            alertDialogBuilder
                    .setCancelable(true)
                    .setPositiveButton("Save",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    final String filename = userInput.getText().toString();
                                    if (userInput.getText().toString().isEmpty()) {
                                        Toast.makeText(getActivity(), "File name can't be empty", Toast.LENGTH_SHORT).show();
                                    } else {
                                        saveInternalFolder(input.getText().toString(),filename+".txt");
                                    }

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
    }
    public void saveInternalFolder(String contents,String filename) {
        try
        {
            // Creates a trace file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.
            File traceFile = new File(((getContext())).getExternalFilesDir(null), filename);
            if (!traceFile.exists())
                traceFile.createNewFile();
            // Adds a line to the trace file
            BufferedWriter writer = new BufferedWriter(new FileWriter(traceFile, true /*append*/));
            writer.write(contents);
            writer.close();
            // Refresh the data so it can seen when the device is plugged in a
            // computer. You may have to unplug and replug the device to see the
            // latest changes. This is not necessary if the user should not modify
            // the files.
            MediaScannerConnection.scanFile((getContext()),
                    new String[] { traceFile.toString() },
                    null,
                    null);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
