package weronika.mathballs;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    static float szerokosc;
    static float wysokosc;
    static float akcelerometr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        szerokosc = size.x;
        wysokosc = size.y;
        SensorManager sensor=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensor.registerListener(this, sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        setContentView(new Gra(this));
        // TODO - fullscreen, blokowanie obracania
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        akcelerometr=(event.values[0])*1.8f;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
