package com.example.diegoeg.ap.udenar.calculator;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import com.fathzer.soft.javaluator.DoubleEvaluator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.*;

public class Graph extends AppCompatActivity {

    private XYPlot plot;
    private final int MIN_VALUE = -50;
    private final int MAX_VALUE = 50;
    private final double INCREMENT = 0.1;
    private final String INDEPENDIENT_VARIABLE = "x";
    private DoubleEvaluator evaluator;
    private EditText functionInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        // initialize our XYPlot reference:
        double x = MIN_VALUE,y = 0;
        plot = (XYPlot) findViewById(R.id.plot);
        functionInput = findViewById(R.id.etFunction);
         List<Number> xVals = new ArrayList();
         List<Number> yVals = new ArrayList();
         LineAndPointFormatter lineAndPointFormatter = new LineAndPointFormatter(
                Color.rgb(255,0,0),
                Color.TRANSPARENT,
                Color.TRANSPARENT,null);
        for (int i = MIN_VALUE; i < MAX_VALUE; i++){
            x = x +  0.1;
            y = Math.sin(x);
            xVals.add(x); yVals.add(y);
        }

        XYSeries xySeries = new SimpleXYSeries(xVals,yVals,"Function");

        PanZoom.attach(plot);
        plot.getOuterLimits().set(MIN_VALUE, MAX_VALUE, MIN_VALUE, MAX_VALUE);
        plot.addSeries(xySeries,lineAndPointFormatter);

    }

    public void graficarExpresion(View v){
        String input = functionInput.getText().toString();
        if (!input.isEmpty() || !input.contains("x")) {

            List<Number> xVals = new ArrayList();
            List<Number> yVals = new ArrayList();
            LineAndPointFormatter lineAndPointFormatter = new LineAndPointFormatter(
                    Color.rgb(255,0,0),
                    Color.TRANSPARENT,
                    Color.TRANSPARENT,null);
            Expression expression = new ExpressionBuilder(input).variable(INDEPENDIENT_VARIABLE).build();
            double dx = MIN_VALUE;

            for (int j = MIN_VALUE*10; j < MAX_VALUE*10; j++) {
                dx += INCREMENT;
                expression.setVariable(INDEPENDIENT_VARIABLE, dx);
                Double result = expression.evaluate();
                xVals.add(dx);
                yVals.add(result);
                //Toast.makeText(this, "VALOR X " + result, Toast.LENGTH_SHORT).show();
            }

            XYSeries xySeries = new SimpleXYSeries(xVals,yVals,"Function");
            plot.clear();
            plot.getOuterLimits().set(-10, 10, -10, 10);
            plot.addSeries(xySeries,lineAndPointFormatter);
        }else
            Toast.makeText(this,"Ingresa una funcion de X",Toast.LENGTH_SHORT).show();

    }

    private int getMax(List<Number> a){

        int max = a.get(0).intValue();

        for (Number i : a){
            max = (max >= i.intValue()) ? max : i.intValue();
        }
        return  max;
    }

    private int getMin(List<Number> a){

        int min = a.get(0).intValue();

        for (Number i : a){
            min = (min < i.intValue()) ? min : i.intValue();
        }
        return  min;
    }
}
