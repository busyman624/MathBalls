package weronika.mathballs;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

public class Kulka {

    Bitmap grafika;
    Random random;
    float x;
    float y;
    float szerokosc;
    float wysokosc;

    public Kulka(ArrayList<Bitmap> kulki){
        grafika=kulki.get(random.nextInt(kulki.size()));
        szerokosc=grafika.getWidth();
        wysokosc=grafika.getHeight();
        y=-wysokosc;
        x=random.nextFloat()*(MainActivity.szerokosc-szerokosc);
    }

    public void update(){
        y=y+15;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika, x, y, null);
    }
}
