package com.sharvilshah.healthmonitoringui;

/**
 * Created by Sharvil on 09/30/2016.
 */
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class sensorHandlerClass extends Service implements SensorEventListener{
    private SensorManager accelManage;
    private Sensor senseAccel;
//    float[] accelValuesX = new float[128];
//    float[] accelValuesY = new float[128];
//    float accelValuesZ[] = new float[128];
    float accelValuesX, accelValuesY, accelValuesZ;
    int index = 0;
    int k=0;
    Bundle b;
    @Override
    public void onCreate(){
        Toast.makeText(sensorHandlerClass.this, "Service Started", Toast.LENGTH_LONG).show();
        accelManage = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senseAccel = accelManage.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelManage.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // TODO Auto-generated method stub
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            index++;
            Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
            accelValuesX = sensorEvent.values[0];
            accelValuesY = sensorEvent.values[1];
            accelValuesZ = sensorEvent.values[2];
//            if (index >= 127) {
//                index = 0;
//                accelManage.unregisterListener(this);
//                accelManage.registerListener(this, senseAccel, SensorManager.SENSOR_DELAY_NORMAL);
//            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub

        return null;
    }

}
