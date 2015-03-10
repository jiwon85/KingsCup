package com.iphoneteam.jiwon_85.cs4084;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

    private short[] deck; //0-12 clovers, 13-25 hearts, 26-38 spades, 39-51 diamonds
    private Button newCardButton;


    private void displayCard(int num){
        //we don't have any graphics yet
    }

    private View.OnClickListener newCardButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            int randomNum;
            Random r = new Random();
            do{
                randomNum = r.nextInt(52); //double check this
            } while(deck[randomNum] != 0);
            TextView temp = (TextView) findViewById(R.id.placeholder);
            temp.setText(""+randomNum);
            displayCard(randomNum);
            deck[randomNum] = 1;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        deck = new short[52];
        newCardButton = (Button) findViewById(R.id.b_new_card);

        newCardButton.setOnClickListener(newCardButtonListener);
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
