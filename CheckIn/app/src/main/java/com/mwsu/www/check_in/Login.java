package com.mwsu.www.check_in;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

public class Login extends AppCompatActivity {
    EditText etUsername, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);



    }

    public void onLogin(View view){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        BackgroundLogin backgroundLogin = new BackgroundLogin(this);
        String result = "";
        try {
            result = backgroundLogin.execute(username,password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Decide if to go to student or prof???
        if(result.equals("Login success")) {
            Intent intent = new Intent(Login.this, StudentHome.class);
            intent.putExtra("username", etUsername.getText().toString());
            startActivity(intent);
        }
        else{
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setTitle("Login Failed");
            builder2.setMessage("Try again...");
            builder2.show();
        }

    }

    public void onSignUp(View view){
        startActivity(new Intent(this, SignUpAs.class));
    }

    //Get rid of the android back function...
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        new android.os.Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
