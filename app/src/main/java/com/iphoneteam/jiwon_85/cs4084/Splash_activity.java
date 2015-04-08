package com.iphoneteam.jiwon_85.cs4084;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;


/*
    Splash activity: For initial splash screen and loading screen
        in between camera image overlay (for filter)
    Authors: Conor Moroney, Ji Won Min
 */

public class Splash_activity extends Activity  {
        private Boolean restart = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_activity);

            Bundle extras = getIntent().getExtras();
            if(extras!=null){
                Boolean flag = extras.getBoolean("LOADING");
                if(flag != null) {
                    restart = true;
                }


            }
            else{
                /****** Create Thread that will sleep for 5 seconds *************/
                Thread background = new Thread() {
                    public void run() {

                        try {
                            // Thread will sleep for 5 seconds
                            sleep(3*1000);

                            // After 5 seconds redirect to another intent
                            Intent i=new Intent(getBaseContext(),MainActivity.class);
                            startActivity(i);



                        } catch (Exception e) {

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

        @Override
        public void onPause(){
            super.onPause();
            if(restart)
                finish();
            else{
                Intent i=new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }
        }


    }