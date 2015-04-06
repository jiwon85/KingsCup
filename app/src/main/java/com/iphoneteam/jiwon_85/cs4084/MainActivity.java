package com.iphoneteam.jiwon_85.cs4084;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {



    private View.OnClickListener startButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(i);
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.b_start_game);
//        startButton.setTypeface(GameActivity.newFont);
        startButton.setOnClickListener(startButtonListener);


        Button DrinkButton;
        // assign web page to SelectedCountry below


        Spinner Country_Spinner = (Spinner)findViewById(R.id.spinner_country);
        //code for selecting country to determine drinking age
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country_array, android.R.layout.simple_spinner_dropdown_item);
        Country_Spinner.setAdapter(adapter);


        String NO_Country = "https://www.google.ie/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=drinking+age+in+";
        String text = Country_Spinner.getSelectedItem().toString();
        String SelectedCountry = NO_Country + text ;
        final String DrinkingAgeURL = SelectedCountry;//was made final for some reason

        DrinkButton = (Button)findViewById(R.id.b_drinking_age);
        DrinkButton.setOnClickListener(new View.OnClickListener() {
            //code to open up web browser
            public void onClick(View v) {
                Intent DrinkAge = new Intent(Intent.ACTION_VIEW,Uri.parse(DrinkingAgeURL));
                startActivity(DrinkAge);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
