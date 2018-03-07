package com.mwsu.www.check_in;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class InstructorSignUpOne extends AppCompatActivity{
    EditText etUsername, etPassword, etPasswordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up_one);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
    }

    public void onNext(View view){
        StringBuilder errorMessage = new StringBuilder ();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Errors");
        builder.setPositiveButton("ok", null);
        boolean userNotTaken = usernameNotTaken(errorMessage);
        boolean EmptyFields = isNotEmpty(errorMessage);
        boolean passwordMatch = checkPasswordsMatch(errorMessage);
        boolean passwordIsSecure = checkPassword(errorMessage);
        boolean lengthIsGood = lengthIsGood(errorMessage);

        if(userNotTaken && EmptyFields && passwordMatch && passwordIsSecure && lengthIsGood) {
            Intent intent = new Intent(InstructorSignUpOne.this, InstructorSignUpTwo.class);
            intent.putExtra("username", etUsername.getText().toString());
            intent.putExtra("password", etPassword.getText().toString());
            startActivity(intent);
        }
        else{
            builder.setMessage(errorMessage);
            builder.show();
        }

    }
    private boolean isNotEmpty(StringBuilder errorMessage) {
        if (etUsername.getText().toString().trim().length() > 0 &&
                etPassword.getText().toString().trim().length() > 0 &&
                etPasswordConfirm.getText().toString().trim().length() > 0){
            return true;
        }
        else{
            errorMessage.append("\u2022 Every field must be filled \n\n");
            return false;
        }
    }
    private boolean checkPasswordsMatch(StringBuilder errorMessage){
        String password = etPassword.getText().toString();
        String passwordCon = etPasswordConfirm.getText().toString();
        if(password.equals(passwordCon)){
            return true;
        }
        else{
            errorMessage.append("\u2022 Passwords do not match \n\n");
            return false;
        }
    }
    private boolean checkPassword(StringBuilder errorMessage){
        int lengthReq = 5;
        String password = etPassword.getText().toString();
        int length = password.length();
        if(length >= lengthReq )
            return true;
        else{
            errorMessage.append("\u2022 Password must be at least 5 characters \n\n");
            return false;
        }
    }
    private boolean usernameNotTaken(StringBuilder errorMessage) {
        String result = "empty";
        String user = etUsername.getText().toString();
        BackgroundUserCheck backgroundUserCheck = new BackgroundUserCheck(this);
        try {
            result = backgroundUserCheck.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(result.equals("User name already taken")) {
            errorMessage.append("\u2022 Username Already Taken \n\n");
            return false;
        }
        return  true;
    }
    private boolean lengthIsGood(StringBuilder errorMessage){
        int maxUser = 40; //How long a user name can be
        int maxPass = 40; //How long a password can be
        String strUser = etUsername.getText().toString();
        String strPass = etPassword.getText().toString();

        if(strUser.length() > maxUser){
            errorMessage.append("\u2022 Username must be less than 40 characters \n\n");
            return false;
        }
        if(strPass.length() > maxPass){
            errorMessage.append("\u2022 Password must be less than 40 characters \n\n");
            return false;
        }
        return true;
    }
}
