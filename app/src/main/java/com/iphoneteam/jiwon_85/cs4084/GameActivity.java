package com.iphoneteam.jiwon_85.cs4084;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

//    private short[] deck; //0-12 clovers, 13-25 hearts, 26-38 spades, 39-51 diamonds
    private Button newCardButton;
    private Button cameraButton;
    private Button questionButton;
    private static Random r = new Random();
    private ImageView suitView;
    private ImageView numView;
    private int randomNum;
    protected final Typeface newFont = Typeface.createFromAsset(this.getAssets(),
            "walkway.ttf");

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
            Card chosen = null;
            do{
                randomNum = r.nextInt(52); //double check this
                chosen = deck[randomNum];
            } while(chosen.played == true);
            TextView temp = (TextView) findViewById(R.id.placeholder);
            temp.setText(""+randomNum);
            chosen.displayImages(suitView, numView);
            Toast.makeText(getApplicationContext(), "suit: "+deck[randomNum].suit+ " num: " + deck[randomNum].num,
                    Toast.LENGTH_SHORT).show();
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
    private View.OnClickListener questionButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            if(randomNum >= 0 && randomNum <= 51) {
                int num = deck[randomNum].num;
                String text = getString(R.string.ace_rule);
                switch(num) {
                    case 1:
                        text = getString(R.string.ace_rule);
                        break;
                    case 2:
                        text = getString(R.string.two_rule);
                        break;
                    case 3:
                        text = getString(R.string.three_rule);
                        break;
                    case 4:
                        text = getString(R.string.four_rule);
                        break;
                    case 5:
                        text = getString(R.string.five_rule);
                        break;
                    case 6:
                        text = getString(R.string.six_rule);
                        break;
                    case 7:
                        text = getString(R.string.seven_rule);
                        break;
                    case 8:
                        text = getString(R.string.eight_rule);
                        break;
                    case 9:
                        text = getString(R.string.nine_rule);
                        break;
                    case 10:
                        text = getString(R.string.ten_rule);
                        break;
                    case 11:
                        text = getString(R.string.jack_rule);
                        break;
                    case 12:
                        text = getString(R.string.queen_rule);
                        break;
                    case 13:
                        text = getString(R.string.king_rule);
                        break;
                    default:
                        //do nothing; already initialized;
                }
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_LONG).show();
            }
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
        newCardButton.setTypeface(newFont);
        cameraButton = (Button) findViewById(R.id.b_camera);
        cameraButton.setTypeface(newFont);
        numView = (ImageView) findViewById(R.id.num_view);
        suitView = (ImageView) findViewById(R.id.suit_view);
        questionButton = (Button) findViewById(R.id.b_question);
        questionButton.setTypeface(newFont);
        newCardButton.setOnClickListener(newCardButtonListener);
        cameraButton.setOnClickListener(cameraButtonListener);
        questionButton.setOnClickListener(questionButtonListener);
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
