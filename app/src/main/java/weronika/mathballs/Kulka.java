package weronika.mathballs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class Kulka {

    Bitmap grafika;
    Random random;
    float x;
    float y;
    float szerokosc;
    float wysokosc;
    boolean czy_bomba=false;
    int punkty;
    boolean czy_bonus=false;



    public Kulka(ArrayList<Bitmap> grafiki_kulek, int licznik_kulek){
        random=new Random();
        int indeks=random.nextInt(grafiki_kulek.size());
        grafika=grafiki_kulek.get(indeks);
        switch(indeks){
            case 0:
                punkty=20;
                break;
            case 1:
                punkty=10;
                break;
            case 2:
                punkty=30;
                break;
            case 3:
                punkty=0;
                czy_bomba=true;
                break;
            case 4:
                punkty=20;
                break;
            case 5:
                punkty=10;
                break;
            case 6:
                punkty=30;
                break;
        }

        if(licznik_kulek%5==0){
            czy_bonus=true;
        }

        szerokosc=grafika.getWidth();
        wysokosc=grafika.getHeight();
        y=-wysokosc;
        x=random.nextFloat()*(MainActivity.szerokosc-szerokosc);

    }

    public Rect pobierz_prostokat(){
        return new Rect((int)x, (int)y, (int)(x+szerokosc), (int)(y+wysokosc));
    }

    public void update(int poziom){
        y=y+6+poziom/3;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika, x, y, null);
    }
}
