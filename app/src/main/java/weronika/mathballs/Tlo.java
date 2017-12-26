package weronika.mathballs;


import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Tlo {

    public Bitmap grafika;

    public Tlo(Bitmap grafika){
        this.grafika=grafika;
    }


    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika,0, 0, null);
    }

}
