package weronika.mathballs;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class Gra extends SurfaceView implements SurfaceHolder.Callback {

    public Petla petla;
    public Tlo tlo;
    public Koszyk koszyk;
    public ArrayList<Bitmap> kulki;

    public Gra(Context kontekst){
        super(kontekst);
        getHolder().addCallback(this);
        petla=new Petla(this, getHolder());
        setFocusable(true);
        kulki=new ArrayList<>();
        kulki.add((BitmapFactory.decodeResource(getResources(), R.drawable.niebieska)));
        kulki.add((BitmapFactory.decodeResource(getResources(), R.drawable.pomaranczowa)));
        kulki.add((BitmapFactory.decodeResource(getResources(), R.drawable.mietowa)));

    }

    public void update(){

        koszyk.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        tlo.draw(canvas);
        koszyk.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        tlo=new Tlo(BitmapFactory.decodeResource(getResources(), R.drawable.b));
        koszyk=new Koszyk(BitmapFactory.decodeResource(getResources(), R.drawable.basket));
        petla.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry)
        {
            try{petla.dziala=false;
                petla.join();
                retry = false;
            }catch(InterruptedException e){e.printStackTrace();}
        }

    }
}
