package com.example.diegoeg.ap.udenar.calculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Lienzo extends View {

    private Context context;
    private boolean isFirsrDisplay;
    private int zoom;
    public final int HEIGHT_SEPARATION =  (int) Math.pow(2,5);
    public final int WIDTH_SEPARATION = (int) Math.pow(2,5);
    //40 y 45 OK


    public Lienzo(Context context) {
        super(context);
        this.isFirsrDisplay = true;
        this.zoom = (int) Math.pow(2,5);
        this.context = context;
    }
    public Lienzo(Context context, int zoom){
        super(context);
        this.isFirsrDisplay = false;
        this.zoom = zoom;
        this.context = context;
    }
    @Override

        protected void onDraw(Canvas canvas) {

        if(this.isFirsrDisplay){
            canvas.drawRGB(4, 63, 140);
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            int center[] = new int[2];
            center[0] = width/2; center[1] = height/2;
            Paint pincel1 = new Paint();
            pincel1.setARGB(255, 255, 255, 255);

            //Drawing axies 0 -> x  1 -> y
            int sizeScreen[] = getSizeScreen();

            pincel1.setStrokeWidth(10);
            //Drawing X
            canvas.drawLine(0, center[1], width, center[1], pincel1);
            //pincel1.setStrokeWidth(4);
            //Drawing Y
            canvas.drawLine(center[0], 0, center[0], height, pincel1);

            //Drawing vertical axies
            pincel1.setStrokeWidth(3);
            for (int i = 0; i<width; i+= WIDTH_SEPARATION){
                canvas.drawLine(i, 0, i, height, pincel1);
            }

            //Drawing horizontal axies
            for (int i = 0; i<height; i+= HEIGHT_SEPARATION){
                canvas.drawLine(0,i , width, i, pincel1);
            }

        /*
        pincel1.setARGB(255, 0, 0, 0);
        //Drawing f(x) = x function
        for (int i = 0; i<width; i++){
            for (int j =0; i<height;j++){
                if(i==j){
                    canvas.drawPoint(i,j,pincel1);
                }
            }
        }
        */

        isFirsrDisplay=false;
        }

        else{
            //Repaint screen
            canvas.drawRGB(4, 63, 140);
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            int center[] = new int[2];
            center[0] = width/2; center[1] = height/2;
            Paint pincel1 = new Paint();
            pincel1.setARGB(255, 255, 255, 255);

            //Drawing axies 0 -> x  1 -> y
            int sizeScreen[] = getSizeScreen();

            pincel1.setStrokeWidth(10);
            //Drawing X
            canvas.drawLine(0, center[1], width, center[1], pincel1);
            //pincel1.setStrokeWidth(4);
            //Drawing Y
            canvas.drawLine(center[0], 0, center[0], height, pincel1);

            //Drawing vertical axies
            pincel1.setStrokeWidth(3);
            for (int i = 0; i<width;){
                canvas.drawLine(i, 0, i, height, pincel1);
                i += Math.pow(2,this.zoom);
            }

            //Drawing horizontal axies
            for (int i = 0; i<height;){
                canvas.drawLine(0,i , width, i, pincel1);
                i += Math.pow(2,this.zoom);
            }

        }


            /*
            Graph View
             */

    }

    private int[] getSizeScreen(){
        int [] size = new int[2];
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point s = new Point();
        display.getSize(s);
        int width = s.x;
        int height = s.y;

        size[0] = s.x;  size[1]= s.y;

        Log.i("Width", "" + width);
        Log.i("height", "" + height);

        return size;
    }

    public int getZoom(){
        return this.zoom;
    }

    public void setZoom(int z){
        this.zoom = z;
    }
}


