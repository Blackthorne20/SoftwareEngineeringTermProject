package com.mwsu.www.check_in;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpAs extends AppCompatActivity implements View.OnClickListener {

    Button bStudent;
    Button bInstructor;
    Button bReturntologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as);

        bStudent = (Button) findViewById(R.id.bStudent);
        bInstructor = (Button) findViewById(R.id.bInstructor);
        bReturntologin = (Button) findViewById(R.id.bReturntologin);

        bStudent.setOnClickListener(this);
        bInstructor.setOnClickListener(this);
        bReturntologin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.bStudent:
                startActivity(new Intent(this,StudentSignUpOne.class));
                break;
            case R.id.bInstructor:
                break;
            case R.id.bReturntologin:
                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}
