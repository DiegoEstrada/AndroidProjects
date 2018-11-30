package com.example.diegoeg.ap.udenar.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Grafica extends AppCompatActivity {

    private final int MIN_VALUE = -50;
    private final int MAX_VALUE = 50;
    private final double INCREMENT = 0.01;
    private final String INDEPENDIENT_VARIABLE = "x";
    //private final double LOCAL_INFINITY = 9999;
    private DoubleEvaluator evaluator;
    private GraphView graph;
    private EditText functionInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);
        evaluator = new DoubleEvaluator();

        double x = MIN_VALUE,y = 0;
        graph = (GraphView) findViewById(R.id.gpvGrafica);
        functionInput = findViewById(R.id.etFuncionX);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        //series.setDrawDataPoints(true;



        for (int i = MIN_VALUE; i < MAX_VALUE; i++){
            x = x +  0.1;
            y = Math.sin(x);

            series.appendData(new DataPoint(x,y),true,MAX_VALUE);
        }


        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-1);
        graph.getViewport().setMaxY(1);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);

        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(series);
    }

    public void graficarExpresion(View v ){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        graph.removeAllSeries();

        String input = functionInput.getText().toString();
        if (!input.isEmpty() || !input.contains("x")) {


            Expression expression = new ExpressionBuilder(input).variable(INDEPENDIENT_VARIABLE).build();
            double dx = MIN_VALUE;

            for (int j = MIN_VALUE; j < MAX_VALUE; j++) {
                dx += INCREMENT;
                expression.setVariable(INDEPENDIENT_VARIABLE, dx);
                Double result = expression.evaluate();
                series.appendData(new DataPoint(dx, result), true, MAX_VALUE);
                //Toast.makeText(this, "VALOR X " + result, Toast.LENGTH_SHORT).show();

            }

            // enable scaling and scrolling
            //graph.getViewport().setScalable(true);
            //graph.getViewport().setScalableY(true);

            // activate horizontal zooming and scrolling
            graph.getViewport().setScalable(true);

// activate horizontal scrolling
            graph.getViewport().setScrollable(true);

// activate horizontal and vertical zooming and scrolling
            graph.getViewport().setScalableY(true);

// activate vertical scrolling
            graph.getViewport().setScrollableY(true);

            /*

            // Defining range
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(MIN_VALUE);
            graph.getViewport().setMaxX(MAX_VALUE);

            // Defining image
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(-5);
            graph.getViewport().setMaxY(5);

            */

            graph.addSeries(series);
        }else
            Toast.makeText(this,"Ingresa una funcion de X",Toast.LENGTH_SHORT).show();

    }

    private int getMaxFrom(String mathExpression){
        int max = 0;
        double r,b=0;
        Expression expression = new ExpressionBuilder(mathExpression).variable(INDEPENDIENT_VARIABLE).build();
        for (int k = 0; k < 10000; k+=10){
            expression.setVariable(INDEPENDIENT_VARIABLE,k);
            r = expression.evaluate();

            if(b>r){
                max = (int)b;
                break;
            }else
                b = r;

        }

        return max;
    }
}
