package com.example.diegoeg.ap.udenar.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Historial extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        tv = findViewById(R.id.tvHistorial);
        tv.setText(getHistory());
    }






    public String getHistory(){
        String h = "";


        SharedPreferences prefe = getSharedPreferences(MainActivity.HISTORY_KEY, Context.MODE_PRIVATE);
        String d=prefe.getString(MainActivity.HISTORY_KEY,"");
        if (d.length()==0) {
            Toast.makeText(this,""+getString(R.string.tstSinDatosGuardados),Toast.LENGTH_LONG).show();
        }
        else {
            h = d;
        }

        return  h;
    }

    public void regresarMenu(View v) throws Exception{

        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }

    public void deleteHistory(View v){
        SharedPreferences preferencias=getSharedPreferences(MainActivity.HISTORY_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(MainActivity.HISTORY_KEY, "");
        editor.commit();
        Toast.makeText(this,""+getString(R.string.tstDatosEliminados),Toast.LENGTH_LONG).show();
        tv.setText("");
    }

}
