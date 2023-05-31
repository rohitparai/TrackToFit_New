package com.swatitiwari.tracktofit.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.swatitiwari.tracktofit.R;
import com.swatitiwari.tracktofit.fitnessCalculator.FitnessMainActivity;

public class UserTimeline extends AppCompatActivity {

    private ImageView imBack;
    private CardView cvWater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_timeline);
        cvWater = findViewById(R.id.cvWaterActivity);
        imBack = findViewById(R.id.imBack);

        cvWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserTimeline.this,WaterActivity.class));
                finish();
            }
        });

        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserTimeline.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UserTimeline.this, MainActivity.class));
        finish();
    }
}