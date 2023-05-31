package com.swatitiwari.tracktofit.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;
import com.swatitiwari.tracktofit.R;
import com.swatitiwari.tracktofit.fitnessCalculator.FitnessMainActivity;
import com.swatitiwari.tracktofit.fitnessCalculator.FrontActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    CardView cvTimeline,cvQuiz,cvDashboard,cvDietFood;
    private String loggedIn;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION},
                    100);
        }
      /*  PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                //Toast.makeText(FrontActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT)
                        .show();
            }


        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("Grant Permissions for this project")
                .setDeniedMessage("Denied")
                .setPermissions(Manifest.permission.CAMERA)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                .setPermissions(Manifest.permission.ACTIVITY_RECOGNITION)
                .setPermissions(Manifest.permission.READ_PHONE_STATE)
                .setPermissions(Manifest.permission.WAKE_LOCK)
                .check();*/
        cvTimeline = findViewById(R.id.cvTimeline);
        cvQuiz = findViewById(R.id.cvQuiz);
        cvDashboard = findViewById(R.id.cvDashboard);
        cvDietFood = findViewById(R.id.cvDietFood);
        loadPreferences();
        cvTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loggedIn.equals("no")) {
//            Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, FitnessMainActivity.class);
                    intent.putExtra("MOB_NUMBER", loggedIn);
                    startActivity(intent);
                    finish();
                }else {
                    startActivity(new Intent(MainActivity.this, FitnessMainActivity.class));
                    Toast.makeText(getApplicationContext(),"Login First!",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        cvQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, com.swatitiwari.tracktofit.Quiz.MainActivity.class));
                finish();
            }
        });
        cvDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loggedIn.equals("no")) {
//            Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, FitnessMainActivity.class);
                    intent.putExtra("MOB_NUMBER", loggedIn);
                    startActivity(intent);
                    finish();
                } else {
                startActivity(new Intent(MainActivity.this, FitnessMainActivity.class));
                Toast.makeText(getApplicationContext(),"Login First!",Toast.LENGTH_LONG).show();
                finish();
            }
            }
        });
        cvDietFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FoodActivity.class));
                finish();
            }
        });
    }
    private void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("usersave", MODE_PRIVATE);
        loggedIn = sharedPreferences.getString("User", "no");
        if (loggedIn.equals("") || loggedIn.isEmpty() || loggedIn.equals("no"))
            loggedIn = "no";
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //Exit App
    protected void exitByBackKey() {

        final Dialog dialog = new Dialog(MainActivity.this);
// Include dialog.xml file
        dialog.setContentView(R.layout.dialog_exit);
        dialog.show();

        Button btnNo = (Button) dialog.findViewById(R.id.btn_no);
        Button btnYes = (Button) dialog.findViewById(R.id.btn_yes);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

// if decline button is clicked, close the custom dialog
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });

    }

}