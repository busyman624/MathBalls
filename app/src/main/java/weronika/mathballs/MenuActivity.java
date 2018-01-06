package weronika.mathballs;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Aktywność wyświetlająca menu początkowe.
 * Uruchamiana przy starcie aplikacji.
 */
public class MenuActivity extends Activity {
    /**
     * Przycisk do rozpoczęcia gry.
     */
    ImageButton start;

    /**
     * Metoda uruchamiana przy starcie aplikacji.
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
        setContentView(R.layout.menu_activity);
        start=(ImageButton)findViewById(R.id.start);
        start.setOnClickListener(new PrzyciskStart());
    }

    /**
     * Klasa obsługująca wciśnięcie przycisku "start".
     */
    public class PrzyciskStart implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startActivity(new Intent(MenuActivity.this, MainActivity.class));
            finish();
        }
    }
}
