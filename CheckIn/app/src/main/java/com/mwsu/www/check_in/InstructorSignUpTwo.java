package com.mwsu.www.check_in;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InstructorSignUpTwo extends AppCompatActivity{
    EditText etFirstname, etLastname, etInstructorid, etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up_two);

        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etInstructorid = (EditText) findViewById(R.id.etInstructorid);
        etEmail = (EditText) findViewById(R.id.etEmail);

    }

    public void onRegister(View view){
        //startActivity(new Intent(this, StudentSignUpTwo.class));
    }
    public void onPrevious(View view){
        startActivity(new Intent(this, InstructorSignUpOne.class));
    }

}
