package com.ace.vishal.helpinghands;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

/**
 * Created by Hp on 1/27/2017.
 */
public class DeafOrganisation extends AppCompatActivity{
    ListView listdeaf;
    String[] organisation = new String[]{"आलू ", "दाल (चना,मसूर,मूंग,उड़द,लोबिया,अरहर)", "धान(बैनी)","धान(स्थानीय)",
            "गेहुँ", "गन्ना (बीजित)", "गन्ना (मोढ़ी)", "मक्का (संकर)","मक्का (स्थानीय)","सूरजमुखी" ,"सरसों,तोरिया"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deaf_organisation);
        listdeaf = (ListView) findViewById(R.id.listdeaf);
        //Declaring Array adapter
        ArrayAdapter<String> cropAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,organisation);

        //Setting the android ListView's adapter to the newly created adapter
        listdeaf.setAdapter(cropAdapter);
        listdeaf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        break;
                }
            }
        });
    }
}
