package com.example.diegoeg.ap.udenar.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.fathzer.soft.javaluator.DoubleEvaluator;




public class MainActivity extends AppCompatActivity {

    private TextView display;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnAdd,btnSubstract,
            btnMultiply,btnDivide,btnClear,btnEqual,btnSin,btnCos,btnTan,btnSqrt,btnPA,
            btnPC,btnX,btnExp,btnLog,btnLn,btnPorc,btnE;
    private String input = "";



    public static final String HISTORY_KEY = "historialOP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

        //Registring event sources

        /*
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSubstract.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        btnSin.setOnClickListener(this);
        btnCos.setOnClickListener(this);
        btnTan.setOnClickListener(this);
        btnSqrt.setOnClickListener(this);
        btnPA.setOnClickListener(this);
        btnPC.setOnClickListener(this);
        btnX.setOnClickListener(this);
        btnExp.setOnClickListener(this);
        btnLog.setOnClickListener(this);
        btnLn.setOnClickListener(this);
        btnPorc.setOnClickListener(this);
        btnE.setOnClickListener(this);
        */

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


    /*

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("displayState",this.display.getText().toString());
        System.out.println("SAVING STATES");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String text = savedInstanceState.getString("displayState");
        this.display.setText(String.valueOf(text));
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.itemAcercaDe ){
            Intent intent = new Intent( MainActivity.this,AcercaDE.class);
            startActivity(intent);
        }
        if (id==R.id.itemHistorial) {
            Intent intent = new Intent( MainActivity.this,Historial.class);
            startActivity(intent);
        }
        if (id==R.id.itemGraficar) {
            Intent intent = new Intent( MainActivity.this,Graph.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
     /*
        Imagge Asset gestiona el manejo del icono de la aplicacion para el icono, notificaciones, etc
        Un scrollLayout solo contiene un elemento dentro de el, que es un Linear Layout

        File, Settings, Plugins, Browse reposuitorues, Android Drawabe Importer, es un plugin para
       no hacer cambios manuales al icono de la aplicacione

       App, new, Batch Drawable Importer

       Para ver el archivo xml del preferenceShared, dentro de data, user, 0, ahi se encuentra el
       nombre de la aplicacion
       Device file explorer


       Nuevo activity en toolbar para dibujar un plano cartesiano, icono, cambiar escalas
     */

     /*
        En la nueva version de Android, android Oreo 8.01, en el archivo manifest hay una linea
        llamada <!--android:roundIcon="@mipmap/ic_launcher_round" --> la cual especifica la direccion
        para el icono de la palicacion con los bordes redondeados, se puede quitar y el icono
        se adapta al circulo o se tienen que modificar el icono con bordes redondos y especificar
        la direccion de estaas imagenes
      */
}
