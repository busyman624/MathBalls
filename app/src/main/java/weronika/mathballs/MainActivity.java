package weronika.mathballs;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Główna aktywność, w której wyświetlany jest przebieg gry.
 * Uruchamiana z aktywności MenuActivity.
 */
public class MainActivity extends Activity implements SensorEventListener {
    /**
     * Szerokość ekranu.
     */
    static float szerokosc;
    /**
     * Wysokość ekranu.
     */
    static float wysokosc;
    /**
     * Wartość składowej siły odczytanej przez akcelerometr.
     */
    static float akcelerometr;

    /**
     * Metoda uruchamiana przy rozpoczęciu gry.
     * Ustawiany jest widok na nowy obiekt klasy Gra.
     * @param savedInstanceState nieużywany
     */
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(new Gra(this));
    }

    /**
     * Metoda uruchamiana przy każdym odczycie z akcelerometru.
     * Aktualizuje wartość pola akcelerometr.
     * @param event wykorzystywany do przekazania danych z akcelerometru do metody
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        akcelerometr=(event.values[0])*1.8f;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Obsługa fizycznego przycisku powrotu.
     */
    @Override
    public void onBackPressed(){
        startActivity(new Intent(MainActivity.this, MenuActivity.class));
        finish();
    }
}
