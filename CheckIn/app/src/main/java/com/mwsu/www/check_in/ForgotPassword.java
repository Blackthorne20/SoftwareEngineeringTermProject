package com.mwsu.www.check_in;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mwsu.www.check_in.BackgroundForgotPass;
import com.mwsu.www.check_in.R;

import java.util.concurrent.ExecutionException;

/**
* Created by Kevin Ellis on 3/19/2018.
*/

public class ForgotPassword extends AppCompatActivity {
    EditText etUsername;
    Button   getPassbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        etUsername = (EditText) findViewById(R.id.etUsername);
        getPassbutton = (Button) findViewById(R.id.getPassword);

    }

    public void onGetPass(View view){
        getPassbutton.setClickable(false);
        String result = "empty";
        String user = etUsername.getText().toString();
        BackgroundForgotPass backgroundForgotPass = new BackgroundForgotPass(this);
        try {
            result = backgroundForgotPass.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Forgot Password");
        builder.setCancelable(false);
        builder.setMessage("Your password has been sent to your email. ");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(ForgotPassword.this, Login.class);
                startActivity(intent);
            }
        });
        builder.show();
    }
}

