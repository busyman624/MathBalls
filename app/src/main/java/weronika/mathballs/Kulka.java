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



    public Kulka(ArrayList<Bitmap> kulki){
        random=new Random();
        int indeks=random.nextInt(kulki.size());
        grafika=kulki.get(indeks);
        switch(indeks){
            case 0:
                punkty=5;
                break;
            case 1:
                punkty=10;
                break;
            case 2:
                punkty=15;
                break;
            case 3:
                punkty=0;
                czy_bomba=true;
                break;
        }

        szerokosc=grafika.getWidth();
        wysokosc=grafika.getHeight();
        y=-wysokosc;
        x=random.nextFloat()*(MainActivity.szerokosc-szerokosc);

    }

    public Rect pobierz_prostokat(){
        return new Rect((int)x, (int)y, (int)(x+szerokosc), (int)(y+wysokosc));
    }

    public void update(){
        y=y+7;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika, x, y, null);
    }
}
