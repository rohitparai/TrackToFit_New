package com.swatitiwari.tracktofit.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.swatitiwari.tracktofit.Common.SharedPref;
import com.swatitiwari.tracktofit.Database.User;
import com.swatitiwari.tracktofit.Database.UserDatabase;
import com.swatitiwari.tracktofit.Database.UserDatabaseClient;
import com.swatitiwari.tracktofit.R;

import java.util.ArrayList;

public class SignIn extends AppCompatActivity {


    EditText etUsername,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SharedPref sharedPref = SharedPref.getInstance();
        if (sharedPref.getUser(this) != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

            etUsername = findViewById(R.id.tvUsername);
            etPassword = findViewById(R.id.tiePassword);
            TextView tvSignUp = findViewById(R.id.tvSignUp);
            Button btnLogin = findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();

                    if (!validaInputs(username, password)) return;

                    LoginUserTask ut = new LoginUserTask(username, password);
                    ut.execute();

                }
            });


            tvSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(SignIn.this, SignUp.class));
                }
            });

        }

        private boolean validaInputs(String username, String password) {

            if (username.isEmpty()){
                Toast.makeText(this, getString(R.string.username_cannot_empty), Toast.LENGTH_SHORT).show();
                return false;
            }

            if (password.isEmpty()){
                Toast.makeText(this, getString(R.string.password_cannot_empty), Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        }

        class LoginUserTask extends AsyncTask<Void, Void, Void> {

            private final String username;
            private final String password;
            private ArrayList<User> users = new ArrayList<>();

            public LoginUserTask(String username, String password) {
                this.username = username;
                this.password = password;
            }

            @Override
            protected Void doInBackground(Void... voids) {
                UserDatabase databaseClient = UserDatabaseClient.getInstance(getApplicationContext());
                users = (ArrayList<User>) databaseClient.userDao().observeAllUser();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                for (User user : users){
                    if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
                        SharedPref sharedPref = SharedPref.getInstance();
                        sharedPref.setUser(SignIn.this,user);
                        startActivity(new Intent(SignIn.this,MainActivity.class));
                        return;
                    }
                }
                Toast.makeText(SignIn.this, "User not exist", Toast.LENGTH_SHORT).show();

            }
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // startActivity(new Intent(SignIn.this, FitnessMainActivity.class));
        finish();
    }
}



