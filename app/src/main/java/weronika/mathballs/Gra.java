package weronika.mathballs;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Gra extends SurfaceView implements SurfaceHolder.Callback {

    public Petla petla;
    public Tlo tlo;
    public Koszyk koszyk;
    public ArrayList<Kulka> kulki;
    public ArrayList<Bitmap> grafiki_kulek;
    public Timer timer=new Timer();
    public Random random=new Random();
    int punkty=0;
    Paint prostokaty;
    Paint tekst;

    public Gra(Context kontekst){
        super(kontekst);
        getHolder().addCallback(this);
        petla=new Petla(this, getHolder());
        setFocusable(true);
        grafiki_kulek =new ArrayList<>();
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.fioletowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.pomaranczowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.mietowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.bomba));
        prostokaty= new Paint();
        prostokaty.setColor(Color.rgb(139, 69, 19));
        tekst=new Paint();
        tekst.setColor(Color.WHITE);
        tekst.setTextSize(0.05f*MainActivity.wysokosc);
    }

    public void update(){

        koszyk.update();
        for(int i=0; i<kulki.size(); i++) {
            kulki.get(i).update();
            if(kulki.get(i).y>MainActivity.wysokosc){
                kulki.remove(i);
            }
            if(koszyk.pobierz_prostokat().intersect(kulki.get(i).pobierz_prostokat())){
                if(kulki.get(i).czy_bomba==false){
                    punkty=punkty+kulki.get(i).punkty;
                }
                else{
                    //TODO - rysowanie bomby
                }
                kulki.remove(i);
                // TODO - nie ?apac bokiem
            }
        }

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        tlo.draw(canvas);
        for(int i=0; i<kulki.size(); i++) {
            kulki.get(i).draw(canvas);
        }
        koszyk.draw(canvas);
        canvas.drawRect(new Rect((int)(0.05*MainActivity.szerokosc), (int)(0.025*MainActivity.wysokosc),
                (int)(0.45*MainActivity.szerokosc), (int)(0.1*MainActivity.wysokosc)), prostokaty);
        canvas.drawText(""+punkty,0.15f*MainActivity.szerokosc ,0.04f*MainActivity.wysokosc, tekst);
        //TODO - poprawi? wysoko?? tekstu i drugi prostok?t z levelem
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        tlo=new Tlo(BitmapFactory.decodeResource(getResources(), R.drawable.b));
        koszyk=new Koszyk(BitmapFactory.decodeResource(getResources(), R.drawable.basket));
        kulki=new ArrayList<>();
        timer.schedule(new ListenToTimer(), random.nextInt(2000)+1000);
        petla.start();
    }

    public class ListenToTimer extends TimerTask {

        @Override
        public void run() {
            kulki.add(new Kulka(grafiki_kulek));
            timer.schedule(new ListenToTimer(), random.nextInt(2000)+1000);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry)
        {
            try{petla.dziala=false;
                petla.join();
                retry = false;
            }catch(InterruptedException e){e.printStackTrace();}
        }

    }
}
