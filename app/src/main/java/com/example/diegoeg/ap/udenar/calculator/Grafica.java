package com.example.diegoeg.ap.udenar.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Grafica extends AppCompatActivity {

    private int MIN_VALUE = -500;
    private int MAX_VALUE = 500;
    private DoubleEvaluator evaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica);
        evaluator = new DoubleEvaluator();

        double x = MIN_VALUE,y = 0;
        GraphView graph = (GraphView) findViewById(R.id.gpvGrafica);
        EditText functionInput = findViewById(R.id.etFuncionX);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();
        //series.setDrawDataPoints(true;



        for (int i = MIN_VALUE; i < MAX_VALUE; i++){
            x = x +  0.1;
            y = Math.sin(x);

            series.appendData(new DataPoint(x,y),true,500);
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
}
