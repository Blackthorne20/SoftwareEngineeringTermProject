package com.mwsu.www.check_in;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class InstructorSignUpTwo extends AppCompatActivity {
    EditText etFirstname, etLastname, etInstructorid, etEmail;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sign_up_two);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        etFirstname = (EditText) findViewById(R.id.etFirstname);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etInstructorid = (EditText) findViewById(R.id.etInstructorid);
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
        String str_instructorid = etInstructorid.getText().toString();
        String str_Email = etEmail.getText().toString();

        boolean emailNotTaken = emailNotTaken(errorMessage);
        boolean EmptyFields = isNotEmpty(errorMessage);

        if(emailNotTaken && EmptyFields) {
            String result = "empty";
            BackgroundRegisterInstructor backgroundRegisterInstructor = new BackgroundRegisterInstructor(this);
            try {
                result = backgroundRegisterInstructor.execute(str_username,str_firstname,str_lastname,str_instructorid,str_Email,str_password).get();
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
                        startActivity(new Intent(InstructorSignUpTwo.this, Login.class));
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
                etInstructorid.getText().toString().trim().length() > 0  &&
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
    private boolean lengthIsGood(StringBuilder errorMessage){
        int maxFirst = 40; //How long a user name can be
        int maxLast = 40; //How long a password can be
        int maxIid = 40;
        int maxEmail = 50;
        String strFirst = etFirstname.getText().toString();
        String strLast = etLastname.getText().toString();
        String strIid = etInstructorid.getText().toString();
        String strEmail = etEmail.getText().toString();


        if(strFirst.length() > maxFirst){
            errorMessage.append("\u2022 First name must be less than 40 characters \n\n");
            return false;
        }
        if(strLast.length() > maxLast){
            errorMessage.append("\u2022 Last name must be less than 40 characters \n\n");
            return false;
        }
        if(strIid.length() > maxIid){
            errorMessage.append("\u2022 Instructor id must be less than 40 characters \n\n");
            return false;
        }
        if(strEmail.length() > maxEmail){
            errorMessage.append("\u2022 Email must be less than 50 characters \n\n");
            return false;
        }
        return true;
    }
}
