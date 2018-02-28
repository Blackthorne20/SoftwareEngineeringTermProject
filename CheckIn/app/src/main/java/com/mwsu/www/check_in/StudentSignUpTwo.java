package com.mwsu.www.check_in;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class StudentSignUpTwo extends AppCompatActivity {
    EditText etFirstname, etLastname, etMnumber, etEmail;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up_two);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etMnumber = (EditText) findViewById(R.id.etMnumber);
        etEmail = (EditText) findViewById(R.id.etEmail);

    }

    public void onRegister(View view){
        String str_username = username;
        String str_password = password;
        String str_firstname = etFirstname.getText().toString();
        String str_lastname = etLastname.getText().toString();
        String str_mnumber = etMnumber.getText().toString();
        String str_Email = etEmail.getText().toString();
        String type = "Register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,str_username,str_firstname,str_lastname,str_mnumber,str_Email,str_password);

        startActivity(new Intent(this, Login.class));
    }

    public void onPrevious(View view){
        startActivity(new Intent(this, StudentSignUpOne.class));
    }

}
