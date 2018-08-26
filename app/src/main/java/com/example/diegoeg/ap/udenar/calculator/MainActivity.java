package com.example.diegoeg.ap.udenar.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView display;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnAdd,btnSubstract,
            btnMultiply,btnDivide,btnClear,btnEqual;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        btnClear = (Button) findViewById(R.id.buttonC) ;
        btnEqual = (Button) findViewById(R.id.buttonEqual);

        display = (TextView) findViewById(R.id.textView3);

        //Registring event sources


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

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button0:
                input = input.concat("0");
                break;

            case R.id.button1:
                input = input.concat("1");
                break;

            case R.id.button2:
                input = input.concat("2");
                break;

            case R.id.button3:
                input = input.concat("3");
                break;

            case R.id.button4:
                input = input.concat("4");
                break;

            case R.id.button5:
                input = input.concat("5");
                break;

            case R.id.button6:
                input = input.concat("6");
                break;

            case R.id.button7:
                input = input.concat("7");
                break;

            case R.id.button8:
                input = input.concat("8");
                break;

            case R.id.button9:
                input = input.concat("9");
                break;

            case R.id.buttonAdd:
                input = input.concat("+");
                break;

            case R.id.buttonSubtract:
                input = input.concat("-");
                break;

            case R.id.buttonMultiply:
                input = input.concat("*");
                break;

            case R.id.buttonDivide:
                input = input.concat("/");
                break;

            case R.id.buttonEqual:
                input = input.concat("=");
                break;

            case R.id.buttonC:
                if(input.length()>0)
                    input = input.substring(0,input.length()-1);
                else
                    System.out.println("Can not clean more");
                break;

                default:
                    System.out.println("There are no buttons for your choose");
                 break;
        }

        display.setText(""+input);
    }
}
