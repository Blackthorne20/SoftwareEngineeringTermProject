package com.mwsu.www.check_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Kevin Ellis on 3/23/2018.
 */

public class QRScannerCheckin extends AppCompatActivity {
    String username;
    String className;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final Activity activity = this;

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        className = intent.getStringExtra("classname");


        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setOrientationLocked(true);
        integrator.setPrompt("");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "you cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else{
                String classID = result.getContents();
                StringBuilder timeID = new StringBuilder();
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                int timeHour = calendar.get(Calendar.HOUR_OF_DAY);
                int timeMin = calendar.get(Calendar.MINUTE);

                if(day == 2 || day == 4 || day == 6 ){ //Mon Wed and Fri
                    timeID.append("MWF");
                    if(timeMin <  50){
                        timeID.append(timeHour);
                    }
                }
                else if(day == 3 || day == 5){ //tuesday and thursday
                    timeID.append("TR");
                    if(timeMin >= 0 && timeMin < 20){
                        timeID.append(timeHour);
                    }
                    else if(timeMin >= 20 && timeMin < 50){
                        timeID.append(timeHour);
                        timeID.append(30);
                    }
                }
                BackgroundCheckIntoClass backgroundCheckIntoClass = new BackgroundCheckIntoClass(this);
                String result1 = "";
                try {
                    result1 = backgroundCheckIntoClass.execute(username,classID, timeID.toString(),className).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                //SLOW AF NEEDS FIXED?????
                Intent intent = new Intent(QRScannerCheckin.this, StudentHome.class);
                startActivity(intent);
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
