package com.mwsu.www.check_in;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    Button bSignup;
    Button bForgotpassword;
    EditText etUsername;
    EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        bSignup = (Button) findViewById(R.id.bSignup);
        bForgotpassword = (Button) findViewById(R.id.bForgotpassword);

        bLogin.setOnClickListener(this);
        bSignup.setOnClickListener(this);
        bForgotpassword.setOnClickListener(this);
}
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bLogin:
                break;
            case R.id.bSignup:
                startActivity(new Intent(this,SignUpAs.class));
                break;
            case R.id.bForgotpassword:
                break;
        }
    }
}
