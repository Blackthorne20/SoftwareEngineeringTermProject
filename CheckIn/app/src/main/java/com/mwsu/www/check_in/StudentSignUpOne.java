package com.mwsu.www.check_in;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentSignUpOne extends AppCompatActivity{
    EditText etUsername, etPassword, etPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up_one);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
    }

    public void onNext(View view){

        Intent intent = new Intent(StudentSignUpOne.this, StudentSignUpTwo.class);
        intent.putExtra("username",etUsername.getText().toString());
        intent.putExtra("password",etPassword.getText().toString());
        startActivity(intent);
    }
}
