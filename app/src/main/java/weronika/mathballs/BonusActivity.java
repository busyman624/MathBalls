package weronika.mathballs;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BonusActivity extends Activity {

    TextView pytanie;
    ArrayList<Button> przyciski=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_bonus);
        pytanie=(TextView) findViewById(R.id.pytanie);
        przyciski.add((Button) findViewById(R.id.przycisk1));
        przyciski.add((Button)findViewById(R.id.przycisk2));
        przyciski.add((Button)findViewById(R.id.przycisk3));
        przyciski.add((Button)findViewById(R.id.przycisk4));
        Random random=new Random();
        int x;
        int y;
        int wynik;
        String tekst_pytania;
        int numer_wyniku=random.nextInt(4);
        int zakres_wyniku;

        switch(Gra.poziom/5){
            case 0:
                x=random.nextInt(5);
                y=random.nextInt(5);
                zakres_wyniku=50;
                break;
            case 1:
                x=random.nextInt(5)+5;
                y=random.nextInt(5)+5;
                zakres_wyniku=150;
                break;
            case 2:
                x=random.nextInt(5)+10;
                y=random.nextInt(5)+10;
                zakres_wyniku=300;
                break;
            default:
                x=random.nextInt(5)+15;
                y=random.nextInt(5)+15;
                zakres_wyniku=500;
                break;
        }
        wynik=x*y;
        tekst_pytania=x + "x" + y + "=";
        pytanie.setText(tekst_pytania);
        for(int i=0; i<przyciski.size(); i++){
            if(i==numer_wyniku){
                przyciski.get(i).setText(""+wynik);
                przyciski.get(i).setOnClickListener(new PrawidlowaOdpowiedz());
            }
            else{
                przyciski.get(i).setText(""+random.nextInt(zakres_wyniku));
                przyciski.get(i).setOnClickListener(new ZlaOdpowiedz());
                // TODO - wyeliminowac wylosowanie wyniku
            }
        }
    }
    private class PrawidlowaOdpowiedz implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Gra.punkty=Gra.punkty+20;
            finish();
        }
    }
    private class ZlaOdpowiedz implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
