package com.iphoneteam.jiwon_85.cs4084;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;



public class Splash_activity extends Activity  {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_activity);

// METHOD 1

            /****** Create Thread that will sleep for 5 seconds *************/
            Thread background = new Thread() {
                public void run() {

                    try {
                        // Thread will sleep for 5 seconds
                        sleep(5*1000);

                        // After 5 seconds redirect to another intent
                        Intent i=new Intent(getBaseContext(),MainActivity.class);
                        startActivity(i);

                        //Remove activity
                        finish();

                    } catch (Exception e) {

                    }
                }
            };

            // start thread
            background.start();
        }

        @Override
        protected void onDestroy() {

            super.onDestroy();

        }
    }