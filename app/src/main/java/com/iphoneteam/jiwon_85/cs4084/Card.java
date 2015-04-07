package com.iphoneteam.jiwon_85.cs4084;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jiwon_85 on 3/25/15.
 */
public class Card {
    // 0: spades, 1: hearts, 2: clubs, 3:diamonds
    protected int suit;
    //A=1,2,3,4,5,6,7,8,9,10,j=11,q=12,k=13
    protected int num;
    protected boolean played;

    public Card(int new_num, int new_suit) {
        suit = new_suit;
        played = false;
        num = new_num;
    }

    public void displayImages(ImageView suit_view, ImageView num_view){
        int chosen_suit = R.drawable.spades;
        int chosen_num = R.drawable.ace;
        switch (suit) {
            case 0:
                chosen_suit = R.drawable.spades;
                break;
            case 1:
                chosen_suit = R.drawable.hearts;
                break;
            case 2:
                chosen_suit = R.drawable.clubs;
                break;
            case 3:
                chosen_suit = R.drawable.diamonds;
                break;
            default:
                //do nothing; already initialized
        }

        switch(num) {
            case 1:
                chosen_num = R.drawable.ace;
                break;
            case 2:
                chosen_num = R.drawable.two;
                break;
            case 3:
                chosen_num = R.drawable.three;
                break;
            case 4:
                chosen_num = R.drawable.four;
                break;
            case 5:
                chosen_num = R.drawable.five;
                break;
            case 6:
                chosen_num = R.drawable.six;
                break;
            case 7:
                chosen_num = R.drawable.seven;
                break;
            case 8:
                chosen_num = R.drawable.eight;
                break;
            case 9:
                chosen_num = R.drawable.nine;
                break;
            case 10:
                chosen_num = R.drawable.ten;
                break;
            case 11:
                chosen_num = R.drawable.jack;
                break;
            case 12:
                chosen_num = R.drawable.queen;
                break;
            case 13:
                chosen_num = R.drawable.king;
                break;
            default:
                //do nothing; already initialized;

        }
        suit_view.setImageResource(chosen_suit);
        if(suit%2 == 0) {
            num_view.setColorFilter(Color.BLACK);
        }
        else{
            num_view.setColorFilter(null);
        }

        num_view.setImageResource(chosen_num);
    }

    public void displayText(TextView t) {
        int string_id = R.string.ace;
        switch(num) {
            case 1:
                string_id = R.string.ace;
                break;
            case 2:
                string_id = R.string.two;
                break;
            case 3:
                string_id = R.string.three;
                break;
            case 4:
                string_id = R.string.four;
                break;
            case 5:
                string_id = R.string.five;
                break;
            case 6:
                string_id = R.string.six;
                break;
            case 7:
                string_id = R.string.seven;
                break;
            case 8:
                string_id = R.string.eight;
                break;
            case 9:
                string_id = R.string.nine;
                break;
            case 10:
                string_id = R.string.ten;
                break;
            case 11:
                string_id = R.string.jack;
                break;
            case 12:
                string_id = R.string.queen;
                break;
            case 13:
                string_id = R.string.king;
                break;
            default:
                //do nothing; already initialized;

        }
        t.setText(string_id);
    }




}
