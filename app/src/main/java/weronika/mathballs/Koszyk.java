package weronika.mathballs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Koszyk {

    Bitmap grafika;
    float x;
    float y;
    float szerokosc;
    float wysokosc;

    public Koszyk(Bitmap grafika){
        this.grafika=grafika;
        szerokosc=grafika.getWidth();
        wysokosc=grafika.getHeight();
        x=((MainActivity.szerokosc)/2)-(szerokosc/2);
        y=(MainActivity.wysokosc)*7/8;

    }

    public Rect pobierz_prostokat(){
        return new Rect((int)x, (int)(y+wysokosc*0.4), (int)(x+szerokosc), (int)(y+wysokosc));
    }

    public void update(){

        x=x-MainActivity.akcelerometr;

        if(x<0){
            x=0;
        }

        if(x>(MainActivity.szerokosc-szerokosc)){
            x=MainActivity.szerokosc-szerokosc;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika, x, y, null);
    }
}
