package com.ace.vishal.helpinghands;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Hp on 10/17/2016.
 */
public class Splash extends Activity {
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.splash);
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (    InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent i=new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
