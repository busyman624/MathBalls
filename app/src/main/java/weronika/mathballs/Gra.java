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

/**
 * Klasa, w której rysowane i aktualizowane s? wszystkie elementy gry.
 * Zawarta jest tu logika gry.
 */
public class Gra extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * Obiekt typu Petla, w którym zawiera si? g?ówny w?tek gry.
     */
    public Petla petla;
    /**
     * Obiekt typu Tlo, stanowiacy tlo gry.
     */
    public Tlo tlo;
    /**
     * Obiekt typu koszyk, którym porusza gracz.
     */
    public Koszyk koszyk;
    /**
     * Lista wszystkich kulek obecnych w grze.
     */
    public ArrayList<Kulka> kulki;
    /**
     * Lista obrazków kulek i bomb.
     */
    public ArrayList<Bitmap> grafiki_kulek;
    /**
     * Obiekt typu Timer.
     */
    public Timer timer=new Timer();
    /**
     * Obiekt typu Random.
     */
    public Random random=new Random();
    /**
     * Zmienna przechowuj?ca ilo?? punktów gracza.
     */
    static int punkty;
    /**
     * Zmienna przechowuj?ca aktualny poziom.
     */
    static int poziom;
    /**
     * Styl t?a obiektów GUI.
     */
    Paint prostokaty;
    /**
     * Styl tekstu obiektów GUI.
     */
    Paint tekst;
    /**
     * Zmienna przechowuj?ca ilo?? z?apanych bomb.
     */
    int licznik_bomb=0;
    /**
     * Zmienna zliczaj?ca stworzone kulki.
     */
    int licznik_kulek=0;
    /**
     * Obiekt typu MainActivity, s?u??cy otwieraniu nowych aktywno?ci.
     */
    public MainActivity aktywnosc;

    /**
     * Konstruktor klasy Gra. Inicjalizuje wszystkie elementy gry.
     * @param mainActivity wykorzystywany do wywolania konstruktora nadklasy
     *                     i otwierania innych aktywno?ci
     */
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
        // dwa razy dodawane te same kulki aby zmniejszy? prawdopodobie?stwo wylosowania bobmy
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

    /**
     * Metoda, w której aktualizowane jest po?o?enie wszystkich elementów,
     * usuwanie ich po zderzeniu z koszykiem lub wyj?ciu poza ekran.
     * Aktualizowane s? tak?e elementy GUI.
     * Gra jest ko?czona po zebraniu 3 bomb.
     */
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

    /**
     * Metoda rysuj?ca wszystkie elementy gry oraz GUI.
     * @param canvas p?ótno, na którym s? rysowane elementy gry i GUI.
     */
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
        canvas.drawText(""+punkty,0.7f*MainActivity.szerokosc ,0.09f*MainActivity.wysokosc, tekst);
        canvas.drawText("Poziom: "+poziom, 0.09f*MainActivity.szerokosc, 0.09f*MainActivity.wysokosc, tekst);
        for(int i=0; i<licznik_bomb; i++){
            canvas.drawBitmap(Bitmap.createScaledBitmap(grafiki_kulek.get(3), grafiki_kulek.get(3).getWidth()/2, grafiki_kulek.get(3).getHeight()/2, true),
                    0.05f*MainActivity.szerokosc+i*grafiki_kulek.get(3).getWidth()/2, 0.11f*MainActivity.wysokosc, null);
        }

    }

    /**
     * Metoda tworz?ca i inicjalizuj?ca w?tek oraz timer.
     * @param holder uchwyt do powierzchni
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        petla=new Petla(this, holder);
        petla.start();
        timer=new Timer();
        timer.schedule(new ListenToTimer(), random.nextInt(3000)+1000);
    }

    /**
     * Klasa odpowiadaj?ca za obs?ug? zdarze? timera.
     * Wykorzystywana do dodawania nowych kulek co losowy czas.
     */
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

    /**
     * Metoda zatrzymuj?ca w?tek w momencie zniszczenia powierzchni.
     * @param holder niu?ywany
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean ponow = true;
        while(ponow) {
            try{petla.dziala=false;
                petla.join();
                ponow = false;
            }catch(InterruptedException e){e.printStackTrace();}
        }

    }
}
