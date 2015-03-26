package com.iphoneteam.jiwon_85.cs4084;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by jiwon_85 on 3/25/15.
 */
public class Card {
    // 0: spades, 1: hearts, 2: clubs, 3:diamonds
    protected int suit;
    //A=1,2,3,4,5,6,7,8,9,10,j=11,q=12,k=13
    protected int num;
    protected boolean played;

    public Card(int new_suit, int new_num) {
        suit = new_suit;
        played = false;
        num = new_num;

    }

    public void displayImages(){
        Drawable chosen_suit;
        Drawable chosen_num;
//        ImageView suit_view = (ImageView) findViewById(R.layout.suit_view);
//        ImageView num_view = (ImageView) findViewById(R.layout.num_view);
        switch (suit) {
            case 0:
//                chosen_suit = R.drawable.spades;
                break;
            case 1:
//                chosen_suit = R.drawable.hearts;
                break;
            case 2:
//                chosen_suit = R.drawable.clubs;
                break;
            case 3:
//                chosen_suit = R.drawable.diamonds;
                break;
            default:
                //do nothing
        }

        switch(num) {
            case 1:
//                chosen_num = R.drawable.ace;
                break;
            case 2:
//                chosen_num = R.drawable.two;
                break;
            case 3:
//                chosen_num = R.drawable.three;
                break;
            case 4:
//                chosen_num = R.drawable.four;
                break;
            case 5:
//                chosen_num = R.drawable.five;
                break;
            case 6:
//                chosen_num = R.drawable.six;
                break;
            case 7:
//                chosen_num = R.drawable.seven;
                break;
            case 8:
//                chosen_num = R.drawable.eight;
                break;
            case 9:
//                chosen_num = R.drawable.nine;
                break;
            case 10:
//                chosen_num = R.drawable.ten;
                break;
            case 11:
//                chosen_num = R.drawable.jack;
                break;
            case 12:
//                chosen_num = R.drawable.queen;
                break;
            case 13:
//                chosen_num = R.drawable.king;
                break;
            default:
                //do nothing
        }
//        suit_view.setImageDrawable(chosen_suit);
        if(suit%2 == 0) {
//            num_view.setColorFilter(Color.BLACK);
        }

//        num_view.setImageDrawable(chosen_num);
    }


}
