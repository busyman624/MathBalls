package weronika.mathballs;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Aktywność wyświetlająca informacje o przegranej i menu umożliwiające zakończenie
 * lub ponowne rozpoczęcie gry.Uruchamiana z klasy Gra w momencie, gdy ilość bomb jest równa 3.
 */
public class PrzegranaActivity extends Activity {
    /**
     * Pole wyświetlające ilość punktów zdobytą przez użytkownika.
     */
    TextView punkty_koncowe;
    /**
     * Przycisk zamykający aplikację.
     */
    ImageButton koniec;
    /**
     * Przycisk ponownego rozpoczęcia gry.
     */
    ImageButton graj;

    /**
     * Metoda uruchamiana przy starcie aktywności.
     * Inicjalizuje cały widok.
     * @param savedInstanceState nieużywany
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.przegrana_activity);
        punkty_koncowe=(TextView)findViewById(R.id.punkty_koncowe);
        punkty_koncowe.setText("Punkty: "+Gra.punkty);
        koniec=(ImageButton)findViewById(R.id.koniec);
        graj=(ImageButton)findViewById(R.id.graj);
        koniec.setOnClickListener(new PrzyciskKoniec());
        graj.setOnClickListener(new PrzyciskGraj());
    }

    /**
     * Klasa obsługująca wciśnięcie przycisku "koniec".
     */
    private class PrzyciskKoniec implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            finish();
        }
    }

    /**
     * Klasa obsługująca wciśnięcie przycisku "graj"
     */
    private class PrzyciskGraj implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startActivity(new Intent(PrzegranaActivity.this, MainActivity.class));
            finish();
        }
    }
}
