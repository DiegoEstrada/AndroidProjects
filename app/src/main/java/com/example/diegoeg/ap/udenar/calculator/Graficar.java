package com.example.diegoeg.ap.udenar.calculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Graficar extends AppCompatActivity {
    private  int ZOOM = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficar);
        final Context context = this;
         final RelativeLayout relativeLayout = findViewById(R.id.lytLienzo);
        final Lienzo lienzo = new Lienzo(this);
         relativeLayout.addView(lienzo);
        Button btnPlusZoom = findViewById(R.id.btnPlusZoom);
        Button btnLessZoom = findViewById(R.id.btnLessZoom);

        btnLessZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int z = lienzo.getZoom();
                Toast.makeText(context,getString(R.string.tstZoom)+" "+ZOOM,Toast.LENGTH_SHORT).show();
                Lienzo l = new Lienzo(context,ZOOM--);
                //lienzo.setZoom(z--);
                relativeLayout.addView(l);

            }
        });

        btnPlusZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int z = lienzo.getZoom();
                Toast.makeText(context,""+getString(R.string.tstZoom)+" "+ZOOM,Toast.LENGTH_SHORT).show();
                Lienzo l = new Lienzo(context,ZOOM++);
                //lienzo.setZoom(z--);
                relativeLayout.addView(l);
            }
        });


    }


}
