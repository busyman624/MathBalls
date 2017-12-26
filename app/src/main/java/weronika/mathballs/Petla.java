package weronika.mathballs;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Petla extends Thread{
    public Gra gra;
    public SurfaceHolder holder;
    public Canvas canvas;
    public boolean dziala;

    public Petla(Gra gra, SurfaceHolder holder){
        super();
        this.gra=gra;
        this.holder=holder;
        dziala=true;
    }

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
                canvas = this.holder.lockCanvas();
                synchronized (holder) {

                    this.gra.update();
                    this.gra.draw(canvas);
                }
            } catch (Exception e) {
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
