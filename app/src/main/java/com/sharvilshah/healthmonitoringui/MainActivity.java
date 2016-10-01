package com.sharvilshah.healthmonitoringui;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    SensorManager accelManage;
    Sensor senseAccel;
    SQLiteDatabase db;
    EditText patient_ID;
    EditText patient_age;
    EditText patient_name;
    RadioGroup patient_sex;
    String patient_IDtext;
    String patient_agetext;
    String patient_nametext;
    String patient_sextext;
    float accelValuesX;
    float accelValuesY;
    float accelValuesZ;
    int index=0;
    int count = 0;
    GraphView graphView;
    GraphFragment gv = new GraphFragment();
    Button button;
    Boolean shouldContinue = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        accelManage = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senseAccel = accelManage.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            setContentView(R.layout.activity_main);
        final Button btnRun = (Button) findViewById(R.id.btnRun);
        patient_ID = (EditText) findViewById(R.id.patient_ID);
//        String ID = patient_ID.getText().toString();
//        p.setID(ID);
        patient_age = (EditText) findViewById(R.id.patient_age);
//        String age = patient_age.getText().toString();
//        p.setAge(age);
        patient_name = (EditText) findViewById(R.id.patient_name);
//        String name = patient_name.getText().toString();
//        p.setName(name);
        patient_sex = (RadioGroup) findViewById(R.id.radio);
        final String TABLE_NAME = patient_name +"_"+patient_ID+"_"+patient_age+"_"+patient_sex;
        final String COLUMN_id = "ID";
        final String COLUMN_time = "time";
        final String COLUMN_x = "X axis";
        final String COLUMN_y = "Y axis";
        final String COLUMN_z = "Z axis";

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(count == 0) {
//                   try{
//                       db = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/databaseFolder/myDB", null);
//                       db.beginTransaction();
//                       try {
//                           //perform your database operations here ...
//                           db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
//                                   COLUMN_id + "int NOT NULL AUTO_INCREMENT," +
//                                   COLUMN_time + " TIMESTAMP, " +
//                                   COLUMN_x + " TEXT, " +
//                                   COLUMN_y + " TEXT, " +
//                                   COLUMN_z + " TEXT, " +
//                                   ");" );
//
//                           db.setTransactionSuccessful(); //commit your changes
//                       }
//                       catch (SQLiteException e) {
//                           //report problem
//                       }
//                       finally {
//                           db.endTransaction();
//                       }
//                   }catch (SQLException e){
//
//                       Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                   }

                   onRunClicked(v);

//                   Intent startSenseService = new Intent(MainActivity.this, sensorHandlerClass.class);
                   Toast.makeText(MainActivity.this,"Hello I was clicked",Toast.LENGTH_SHORT).show();
                   count++;
               }
                else
               {

               }
              }
        });

        Button btn1 = (Button) findViewById(R.id.btnStop);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gv == null){
                    Toast.makeText(MainActivity.this, "Trying to call a null object", Toast.LENGTH_LONG).show();

                }else{
                    shouldContinue = false;
                    btnRun.setEnabled(true);
                    getSupportFragmentManager().popBackStack();
                    count = 0;
                }
            }
        });

    }



    public void onRunClicked(View view) {
        Thread background = new Thread(new Runnable() {
            public void run() {
                try {
                    shouldContinue = true;
                    while (shouldContinue) {
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, new GraphFragment()).addToBackStack(null)
                                .commit();
                        Thread.sleep(300);
                        getSupportFragmentManager().popBackStack();
                    }
                } catch (Throwable t) {
                }
            }
        });

        background.start();

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        Sensor mySensor = sensorEvent.sensor;
//        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            index++;
//            accelValuesX = sensorEvent.values[0];
//            accelValuesY = sensorEvent.values[1];
//            accelValuesZ = sensorEvent.values[2];
//            if (index >= 127) {
//                index = 0;
//                accelManage.unregisterListener(this);
//                accelManage.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL);
//            }
//        }
        accelValuesX = sensorEvent.values[0];
        accelValuesY = sensorEvent.values[1];
        accelValuesZ = sensorEvent.values[2];

    }
    protected void onResume() {
        super.onResume();
        accelManage.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelManage.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public void setValuex(float accelValuesX)
    {
        this.accelValuesX = accelValuesX;
    }

    public float getValuex()
    {
        return this.accelValuesX;
    }
    public float setValuey()
    {
        return this.accelValuesY;
    }

    public float getValuey()
    {
        return this.accelValuesY;
    }
    public float setValuez()
    {
        return this.accelValuesZ;
    }

    public float getValuez()
    {
        return this.accelValuesZ;
    }

}



