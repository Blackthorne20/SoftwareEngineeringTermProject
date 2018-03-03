package com.mwsu.www.check_in;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

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
        StringBuilder errorMessage = new StringBuilder ();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Errors");
        builder.setPositiveButton("ok", null);

        String str_username = username;
        String str_password = password;
        String str_firstname = etFirstname.getText().toString();
        String str_lastname = etLastname.getText().toString();
        String str_mnumber = etMnumber.getText().toString();
        String str_Email = etEmail.getText().toString();

        boolean emailNotTaken = emailNotTaken(errorMessage);
        boolean EmptyFields = isNotEmpty(errorMessage);

        if(emailNotTaken && EmptyFields) {
            String result = "empty";
            BackgroundRegisterStudent backgroundRegisterStudent = new BackgroundRegisterStudent(this);
            try {
                result = backgroundRegisterStudent.execute(str_username,str_firstname,str_lastname,str_mnumber,str_Email,str_password).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if(result.equals("Registration Successful")) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Registration Successful");
                builder2.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        startActivity(new Intent(StudentSignUpTwo.this, Login.class));
                    }
                });
                builder2.setMessage("");
                builder2.show();
            }
        }
        else{
            builder.setMessage(errorMessage);
            builder.show();
        }

    }
    public void onPrevious(View view){
        startActivity(new Intent(this, StudentSignUpOne.class));
    }
    private boolean isNotEmpty(StringBuilder errorMessage) {
        if (etFirstname.getText().toString().trim().length() > 0 &&
                etLastname.getText().toString().trim().length() > 0 &&
                etMnumber.getText().toString().trim().length() > 0  &&
                etEmail.getText().toString().trim().length() > 0){
            return true;
        }
        else{
            errorMessage.append("\u2022 Every field must be filled \n\n");
            return false;
        }
    }
    private boolean emailNotTaken(StringBuilder errorMessage) {
        String result = "empty";
        String email = etEmail.getText().toString();
        BackgroundEmailCheck backgroundEmailCheck = new BackgroundEmailCheck(this);
        try {
            result = backgroundEmailCheck.execute(email).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(result.equals("Email already taken")) {
            errorMessage.append("\u2022 Email Already Taken \n\n");
            return false;
        }
        return  true;
    }
}
