package br.fmu.projetoaulan;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class DesenhoActivity extends AppCompatActivity {

    private DesenhoView desenhoView;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float acc;
    private float currentAcc;
    private float lastAcc;
    private static final int ACC_MAX = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desenho);
        desenhoView = findViewById(R.id.desenhoView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                lastAcc = currentAcc;
                currentAcc = x*x + y*y + z*z;
                acc = currentAcc * (currentAcc - lastAcc);
                if ( acc > ACC_MAX ) {
                    desenhoView.clear();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {}
        },sensor, SensorManager.SENSOR_DELAY_FASTEST);

    }
}