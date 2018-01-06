package weronika.mathballs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Klasa odpowiadająca za rysowanie oraz poruszanie się koszyka sterowanego przez gracza.
 */
public class Koszyk {
    /**
     * Obrazek koszyka.
     */
    Bitmap grafika;
    /**
     * Początek (lewy górny róg) koszyka na osi x.
     */
    float x;
    /**
     * Początek (lewy górny róg) koszyka na osi y.
     */
    float y;
    /**
     * Szerokosc wczytanego koszyka.
     */
    float szerokosc;
    /**
     * Wysokosc wczytanego koszyka.
     */
    float wysokosc;

    /**
     * Konstruktor klasy. Inicjalizuje zmienne.
     * @param grafika wykorzystywany do przesyłania obrazka koszyka
     */
    public Koszyk(Bitmap grafika){
        this.grafika=grafika;
        szerokosc=grafika.getWidth();
        wysokosc=grafika.getHeight();
        x=((MainActivity.szerokosc)/2)-(szerokosc/2);
        y=(MainActivity.wysokosc)*7/8;

    }

    /**
     * Metoda zwracająca hitbox.
     * @return zwracany hitbox obiektu
     */
    public Rect pobierz_prostokat(){
        return new Rect((int)x, (int)(y+wysokosc*0.4), (int)(x+szerokosc), (int)(y+wysokosc));
    }

    /**
     * Metoda aktualizująca położenie koszyka na podstawie danych z akcelerometru.
     */
    public void update(){

        x=x-MainActivity.akcelerometr;

        if(x<0){
            x=0;
        }

        if(x>(MainActivity.szerokosc-szerokosc)){
            x=MainActivity.szerokosc-szerokosc;
        }
    }

    /**
     * Metoda rysująca koszyk.
     * @param canvas płótno, na którym ma być narysowany koszyk.
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika, x, y, null);
    }
}
