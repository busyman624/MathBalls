package weronika.mathballs;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Główny wątek gry.
 */
public class Petla extends Thread{
    /**
     * Obiekt typu Gra
     */
    public Gra gra;
    /**
     * Uchwyt do powierzchni, na której tworzona jest gra.
     */
    public SurfaceHolder holder;
    /**
     * Płótno, na którym rysowana jest gra.
     */
    public Canvas canvas;
    /**
     * Zmienna warunkująca działanie właściwej pętli gry.
     */
    public boolean dziala;

    /**
     * Konstruktor klasy Petla inicjalizujący niektóre pola klasy
     * @param gra
     * @param holder
     */
    public Petla(Gra gra, SurfaceHolder holder){
        super();
        this.gra=gra;
        this.holder=holder;
        dziala=true;
    }

    /**
     * Metoda inicjująca nowy wątek aplikacji.
     * Uruchamia metody update oraz draw z klasy Gra 30 razy/s.
     */
    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount =0;
        int FPS = 30;
        long targetTime = 1000/ FPS;

        //właściwa pętla gry

        while(dziala) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = holder.lockCanvas();
                synchronized (holder) {

                    gra.update();
                    gra.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            frameCount++;
            if(frameCount == FPS)
            {
                frameCount =0;
            }
        }
    }
}
