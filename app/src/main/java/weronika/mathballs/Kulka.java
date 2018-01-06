package weronika.mathballs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa służąca do rysowania oraz zmiany położenia kulek i bomb.
 */
public class Kulka {
    /**
     * Obrazek kulki lub bomby.
     */
    Bitmap grafika;
    Random random;
    /**
     * Początek (lewy górny róg) obrazka na osi x.
     */
    float x;
    /**
     * Początek (lewy górny róg) obrazka na osi y.
     */
    float y;
    /**
     * Szerokosc obrazka.
     */
    float szerokosc;
    /**
     * Wysokosc obrazka.
     */
    float wysokosc;
    /**
     * Zmienna do rózróżniania kulek od bomb.
     */
    boolean czy_bomba=false;
    /**
     * Zmienna przechowująca punkty gracza.
     */
    int punkty;
    /**
     * Zmienna warunkująca wystąpienie bonusu.
     */
    boolean czy_bonus=false;


    /**
     * Konstruktor w którym wybierana jest losowo jedna z trzech kulek lub bomba
     * i ustawiana jest losowa szerokość rysowania elementu.
     * W zależności od koloru kulki przypisywana jest liczba punktów.
     * Sprawdzane jest czy kulka zawiera bonus.
     * @param grafiki_kulek
     * @param licznik_kulek
     */
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

    /**
     * Metoda zwracająca hitbox obiektu.
     * @return zwracany hitbox
     */
    public Rect pobierz_prostokat(){
        return new Rect((int)x, (int)y, (int)(x+szerokosc), (int)(y+wysokosc));
    }

    /**
     * Metoda aktualizująca położenie kulki.
     * @param poziom wykorzystywany do zmiany szybkości spadania kulki.
     */
    public void update(int poziom){
        y=y+6+poziom/3;
    }

    /**
     * Metoda rysująca kulkę.
     * @param canvas płótno, na którym ma być rysowana kulka
     */
    public void draw(Canvas canvas){
        canvas.drawBitmap(grafika, x, y, null);
    }
}
