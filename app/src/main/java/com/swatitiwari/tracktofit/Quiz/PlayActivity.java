package com.swatitiwari.tracktofit.Quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.swatitiwari.tracktofit.Activities.MainActivity;
import com.swatitiwari.tracktofit.R;

public class PlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        CardView phy = findViewById(R.id.phy_card);
        // Set a click listener on that View
        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phyIntent = new Intent(PlayActivity.this, PhysicsActivity.class);
                startActivity(phyIntent);
            }
        });

        CardView chem = findViewById(R.id.chem_card);
        // Set a click listener on that View
        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chemIntent = new Intent(PlayActivity.this, ChemistryActivity.class);
                startActivity(chemIntent);
            }
        });

        CardView bio = findViewById(R.id.bio_card);
        // Set a click listener on that View
        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bioIntent = new Intent(PlayActivity.this, BiologyActivity.class);
                startActivity(bioIntent);
            }
        });

        CardView more = findViewById(R.id.more_card);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Patience is the key";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PlayActivity.this, MainActivity.class));
        finish();
    }
}