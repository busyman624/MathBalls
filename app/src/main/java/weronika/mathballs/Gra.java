package weronika.mathballs;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    static int punkty;
    static int poziom;
    Paint prostokaty;
    Paint tekst;
    int licznik_bomb=0;
    int licznik_kulek=0;
    public MainActivity aktywnosc;

    public Gra(MainActivity mainActivity){
        super(mainActivity);
        getHolder().addCallback(this);
        setFocusable(true);
        punkty=0;
        poziom=1;
        tlo=new Tlo(BitmapFactory.decodeResource(getResources(), R.drawable.tlo4));
        koszyk=new Koszyk(BitmapFactory.decodeResource(getResources(), R.drawable.basket));
        kulki=new ArrayList<>();
        grafiki_kulek =new ArrayList<>();
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.fioletowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.pomaranczowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.mietowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.bomba));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.fioletowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.pomaranczowa));
        grafiki_kulek.add(BitmapFactory.decodeResource(getResources(), R.drawable.mietowa));
        prostokaty= new Paint();
        prostokaty.setColor(Color.rgb(139, 69, 19));
        tekst=new Paint();
        tekst.setColor(Color.WHITE);
        tekst.setTextSize(0.035f*MainActivity.wysokosc);
        aktywnosc=mainActivity;
    }

    public void update(){
        koszyk.update();
        for(int i=0; i<kulki.size(); i++) {
            kulki.get(i).update(poziom);
            if(kulki.get(i).y>MainActivity.wysokosc){
                kulki.remove(i);
            }
            if(koszyk.pobierz_prostokat().intersect(kulki.get(i).pobierz_prostokat()) && kulki.get(i).y<koszyk.y){
                if(kulki.get(i).czy_bomba==false){
                    if(kulki.get(i).czy_bonus==true){
                        petla.dziala=false;
                        aktywnosc.startActivity(new Intent(aktywnosc, BonusActivity.class));
                        timer.cancel();
                    }
                    punkty=punkty+kulki.get(i).punkty;
                }
                else{
                    licznik_bomb++;
                    if(licznik_bomb==3){
                        aktywnosc.startActivity(new Intent(aktywnosc, PrzegranaActivity.class));
                        petla.dziala=false;
                        timer.cancel();
                        aktywnosc.finish();
                    }
                }
                kulki.remove(i);
            }
        }
        poziom=punkty/100+1;
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        tlo.draw(canvas);
        for(int i=0; i<kulki.size(); i++) {
            kulki.get(i).draw(canvas);
        }
        koszyk.draw(canvas);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prostokat), 0.05f*MainActivity.szerokosc,
                0.025f*MainActivity.wysokosc, null);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.prostokat), 0.53f*MainActivity.szerokosc, 0.025f*MainActivity.wysokosc, null);
        canvas.drawText(""+punkty,0.66f*MainActivity.szerokosc ,0.075f*MainActivity.wysokosc, tekst);
        canvas.drawText("Poziom: "+poziom, 0.08f*MainActivity.szerokosc, 0.075f*MainActivity.wysokosc, tekst);
        for(int i=0; i<licznik_bomb; i++){
            canvas.drawBitmap(Bitmap.createScaledBitmap(grafiki_kulek.get(3), grafiki_kulek.get(3).getWidth()/2, grafiki_kulek.get(3).getHeight()/2, true),
                    0.05f*MainActivity.szerokosc+i*grafiki_kulek.get(3).getWidth()/2, 0.11f*MainActivity.wysokosc, null);
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        petla=new Petla(this, getHolder());
        petla.start();
        timer=new Timer();
        timer.schedule(new ListenToTimer(), random.nextInt(3000)+1000);
    }

    public class ListenToTimer extends TimerTask {

        @Override
        public void run() {
            licznik_kulek++;
            kulki.add(new Kulka(grafiki_kulek, licznik_kulek));
            timer.schedule(new ListenToTimer(), 2*(random.nextInt(2000)+1000)/poziom);

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try{petla.dziala=false;
                petla.join();
                retry = false;
            }catch(InterruptedException e){e.printStackTrace();}
        }

    }
}
