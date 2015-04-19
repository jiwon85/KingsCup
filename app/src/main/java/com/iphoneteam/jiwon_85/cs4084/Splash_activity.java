package com.iphoneteam.jiwon_85.cs4084;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;


/*
    Splash activity: For initial splash screen and loading screen
        in between camera image overlay (for filter)
    Authors: Conor Moroney, Ji Won Min
 */

public class Splash_activity extends Activity  {
        private Boolean restart;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_activity);
            restart = false;

            Bundle extras = getIntent().getExtras();
            if(extras!=null){
                Boolean flag = extras.getBoolean("LOADING");
                if(flag) {
                    restart = true;
                }


            }

        }

        @Override
        public void onPause(){
            super.onPause();
            if(restart) {
                restart = false;
                finish();
            }

        }

    @Override
    public void onResume(){
        super.onResume();
        if(!restart) {

            Thread background = new Thread() {
                public void run() {

                    try {
                        // Thread will sleep for 2 seconds
                        sleep(2*1000);

                        // After 2 seconds redirect to another intent
                        Intent i=new Intent(getBaseContext(),MainActivity.class);
                        startActivity(i);



                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.error),
                                Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        finish();
                    }
                }
            };

            // start thread
            background.start();
        }
    }


}