package com.example.diegoeg.ap.udenar.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView display;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnAdd,btnSubstract,
            btnMultiply,btnDivide,btnClear,btnEqual,btnSin,btnCos,btnTan,btnSqrt,btnPA,
            btnPC,btnX,btnExp,btnLog,btnLn,btnPorc,btnE;
    private String input = "";

    public static final String HISTORY_KEY = "historialOP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE) ;

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //In landscape
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            //In portrait
        }

        //Initialing buttons
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnSubstract = (Button) findViewById(R.id.buttonSubtract);
        btnMultiply = (Button) findViewById(R.id.buttonMultiply);
        btnDivide = (Button) findViewById(R.id.buttonDivide);
        btnClear = (Button) findViewById(R.id.buttonClear) ;
        btnEqual = (Button) findViewById(R.id.buttonEqual);
        btnSin  = (Button) findViewById(R.id.buttonSin);
        btnCos  = (Button) findViewById(R.id.buttonCos);
        btnTan  = (Button) findViewById(R.id.buttonTan);
        btnSqrt  = (Button) findViewById(R.id.buttonSqrt);
        btnPA  = (Button) findViewById(R.id.buttonPA);
        btnPC  = (Button) findViewById(R.id.buttonPC);
        btnX  = (Button) findViewById(R.id.buttonX);
        btnExp  = (Button) findViewById(R.id.buttonExp);
        btnLog  = (Button) findViewById(R.id.buttonLog);
        btnLn  = (Button) findViewById(R.id.buttonLn);
        btnPorc  = (Button) findViewById(R.id.buttonPorc);
        btnE  = (Button) findViewById(R.id.buttonE);

        display = (TextView) findViewById(R.id.textView3);


        btnClear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                display.setText("");
                input="";
                vibrator.vibrate(3000);
                return true;
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void borrar(View v){
        input = display.getText().toString();
        if(input.length()>0)
            input = input.substring(0,input.length()-1);
        else
            System.out.println("Can not clean more");

        display.setText(input);
    }

    public void verificar(View v){

        String foo = "2*5+(3*8)";
        //ScriptEngineManager mgr = new ScriptEngineManager();
        //ScriptEngine engine = mgr.getEngineByName("JavaScript");

        //Calcular calcular = new Calcular();

        try{


            SharedPreferences preferencias=getSharedPreferences(HISTORY_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencias.edit();
            //engine.eval("2+2");
            // Create a new evaluator
            DoubleEvaluator evaluator = new DoubleEvaluator();
            // Evaluate an expression
            Double result = evaluator.evaluate(input);

            String before = display.getText()+" = "+result+"\n"+getHistory();
            input = result.toString();
            this.display.setText(input);
            editor.putString(HISTORY_KEY, before);
            editor.commit();
            Toast.makeText(this,""+getString(R.string.tstDatosGuardados),Toast.LENGTH_LONG).show();
            //System.out.println(engine.eval(display.getText().toString()));

        }catch(Exception e){
            Log.e("ERROR CANDENA USR",e.getMessage());
        }


    }

    public String getHistory(){
        String h = "";


        SharedPreferences prefe = getSharedPreferences(HISTORY_KEY, Context.MODE_PRIVATE);
        String d=prefe.getString(HISTORY_KEY,"");
        if (d.length()==0) {
            //Toast.makeText(this,"No hay registro de operaciones realizadas ",Toast.LENGTH_LONG).show();
        }
        else {
            h = d;
        }

        return  h;
    }

    public void presiona (View v)
    {
        Button btn = (Button) v;
        String txt = btn.getText().toString();
        /*
        if(v.getId() == (R.id.buttonClear)){
            if(input.length()>0)
                input = input.substring(0,input.length()-1);
            else
                System.out.println("Can not clean more");
        }
        else{
            System.out.println("APPEND "+txt);
            display.setText(display.getText()+""+txt);
        }
        */
        input += txt;
        display.setText(input);

    }


    public void lanzarAcercaDe(View v) throws Exception{
        /*
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = "40+2";
        System.out.println(engine.eval(foo));
        */

        Intent intent = new Intent(this, AcercaDE.class);


        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        //droidondroids

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_Graficar) {
            Intent intent = new Intent( Inicio.this,Historial.class);
            startActivity(intent);
            //finish();
        }
        */
        if (id == R.id.action_AcercaDe){
            Intent intent = new Intent( Inicio.this,AcercaDE.class);
            startActivity(intent);
            //finish();
        }
        /*
        if(id == R.id.action_Graficar){
            Intent intent = new Intent( Inicio.this,Graph.class);
            startActivity(intent);
            //finish();
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            Intent intent = new Intent( Inicio.this,Historial.class);
            startActivity(intent);
        } else if (id == R.id.nav_graph) {
            Intent intent = new Intent( Inicio.this,Graph.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent( Inicio.this,AcercaDE.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
