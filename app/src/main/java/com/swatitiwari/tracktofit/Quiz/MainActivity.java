package com.swatitiwari.tracktofit.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.swatitiwari.tracktofit.Activities.UserTimeline;
import com.swatitiwari.tracktofit.R;

public class MainActivity extends AppCompatActivity {

    CardView play,howTo;
    ImageView imBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        imBack = findViewById(R.id.imBack);
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, com.swatitiwari.tracktofit.Activities.MainActivity.class));
                finish();
            }
        });
         play = findViewById(R.id.cardview1);
        // Set a click listener on that View
        play.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent playIntent = new Intent(MainActivity.this, PlayActivity.class);
                // Start the new activity
                startActivity(playIntent);
            }
        });

         howTo = findViewById(R.id.cardview2);
        // Set a click listener on that View
        howTo.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                Intent howToIntent = new Intent(MainActivity.this, HowTo.class);
                // Start the new activity
                startActivity(howToIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, com.swatitiwari.tracktofit.Activities.MainActivity.class));
        finish();
    }
}