package com.mwsu.www.check_in;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpAs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_as);

    }

    public void onStudent(View view) {
        startActivity(new Intent(this, StudentSignUpOne.class));
    }

    public void onInstructor(View view) {
        startActivity(new Intent(this, InstructorSignUpOne.class));
    }

    public void onReturntologin(View view) {
        startActivity(new Intent(this, Login.class));
    }
}
