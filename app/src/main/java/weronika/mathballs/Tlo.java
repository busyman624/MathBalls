package weronika.mathballs;


import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Klasa odpowiadająca za rysowanie tła.
 */
public class Tlo {
    /**
     * Obrazek tła.
     */
    public Bitmap grafika;

    /**
     * Konstruktor klasy. Inicjalizuje zmienną.
     * @param grafika wykorzystywany do przesyłani obrazka tła
     */
    public Tlo(Bitmap grafika){
        this.grafika=grafika;
    }

    /**
     * Metoda rysująca tło.
     * @param canvas płótno, na którym ma być rysowane tło
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika,0, 0, null);
    }

}
