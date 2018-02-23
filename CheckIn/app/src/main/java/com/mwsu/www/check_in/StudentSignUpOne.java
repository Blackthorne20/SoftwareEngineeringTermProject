package com.mwsu.www.check_in;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentSignUpOne extends AppCompatActivity implements View.OnClickListener {

    Button bNext;
    EditText etUsername;
    EditText etPassword;
    EditText etPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_sign_up1);

        bNext = (Button) findViewById(R.id.bNext);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        bNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bNext:
                break;
        }
    }
}
