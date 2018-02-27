package com.mwsu.www.check_in;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        boolean EmptyFields = isNotEmpty(etUsername,etPassword,etPasswordConfirm);
        boolean passwordMatch = checkPasswordsMatch();
        boolean passwordIsSecure = checkPassword(etPassword);

        if(EmptyFields && passwordMatch && passwordIsSecure){
            Intent intent = new Intent(StudentSignUpOne.this, StudentSignUpTwo.class);
            intent.putExtra("username",etUsername.getText().toString());
            intent.putExtra("password",etPassword.getText().toString());
            startActivity(intent);
        }
    }
    private boolean isNotEmpty(EditText usernameField, EditText passwordField, EditText pconfirmField) {
        if (usernameField.getText().toString().trim().length() > 0 ||
            passwordField.getText().toString().trim().length() > 0 ||
            pconfirmField.getText().toString().trim().length() > 0){
            return true;
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Field Left Blank");
            builder.setMessage("Every field must be filled");
            builder.setPositiveButton("ok", null);
            builder.show();
            return false;
        }
    }
    private boolean checkPasswordsMatch(){
        String password = etPassword.getText().toString();
        String passwordCon = etPasswordConfirm.getText().toString();
        if(password.equals(passwordCon)){
            return true;
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Passwords Do Not Match");
            builder.setMessage("Enter to same password into the confirm field");
            builder.setPositiveButton("ok", null);
            builder.show();
            return false;
        }
    }
    private boolean checkPassword(EditText pass){
        int lengthReq = 5;
        String password = etPassword.getText().toString();
        int length = password.length();
        if(length >= lengthReq )
            return true;
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Password Is Not Long Enough");
            builder.setMessage("Password must be 5 or more characters");
            builder.setPositiveButton("ok", null);
            builder.show();
            return false;
        }
    }
}
