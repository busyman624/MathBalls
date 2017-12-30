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

public class PrzegranaActivity extends Activity {

    TextView punkty_koncowe;
    ImageButton koniec;
    ImageButton graj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.przegrana_activity);
        punkty_koncowe=(TextView)findViewById(R.id.punkty_koncowe);
        punkty_koncowe.setText("Punkty: "+Gra.punkty);
        koniec=(ImageButton)findViewById(R.id.koniec);
        graj=(ImageButton)findViewById(R.id.graj);
        koniec.setOnClickListener(new PrzyciskKoniec());
        graj.setOnClickListener(new PrzyciskGraj());
    }

    private class PrzyciskKoniec implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            finish();
        }
    }

    private class PrzyciskGraj implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startActivity(new Intent(PrzegranaActivity.this, MainActivity.class));
            finish();
        }
    }
}
