package com.iphoneteam.jiwon_85.cs4084;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

//    private short[] deck; //0-12 clovers, 13-25 hearts, 26-38 spades, 39-51 diamonds
    private Button newCardButton;
    private Button cameraButton;
    private static Random r = new Random();

    private Card[] deck;
    private int king_count = 0;

    private View.OnClickListener cameraButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), CameraActivity.class);
            startActivity(i);
        }
    };
    private View.OnClickListener newCardButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            int randomNum;
            Card chosen = null;
            do{
                randomNum = r.nextInt(52); //double check this
                chosen = deck[randomNum];
            } while(chosen.played == true);
            TextView temp = (TextView) findViewById(R.id.placeholder);
            temp.setText(""+randomNum);
            chosen.displayImages();
            if(chosen.suit == 13) {
                king_count++;
                if(king_count == 4) {
                    //game over
                }
            }
            if(chosen.suit == 1) {
                //camera feature
                cameraButton.setVisibility(View.VISIBLE);
            }
            else {
                cameraButton.setVisibility(View.INVISIBLE);
            }
            deck[randomNum].played = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        deck = new Card[52];
        for(int i = 0; i < 52; i++) {
            if(i < 13)
                deck[i] =  new Card((i%13)+1, 0);
            else if(i < 26)
                deck[i] =  new Card((i%13)+1, 1);
            else if(i < 39)
                deck[i] =  new Card((i%13)+1, 2);
            else
                deck[i] =  new Card((i%13)+1, 3);

        }

        newCardButton = (Button) findViewById(R.id.b_new_card);
        cameraButton = (Button) findViewById(R.id.b_camera);
        newCardButton.setOnClickListener(newCardButtonListener);
        cameraButton.setOnClickListener(cameraButtonListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
