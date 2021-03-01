package com.example.gbapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String MY_LOG = "myLog";
    StringBuilder resultText;
    String tmpText = "";
    int countBracket = 0;
    String in, inLine;

    TextView textInput, textOutput;
    Parser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        resultText = new StringBuilder("");

        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        textInput = findViewById(R.id.textInput);
        textOutput = findViewById(R.id.textOutput);
    }

    public void result(View view) {

        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        if (countBracket == 0) {
            if (!(textOutput.getText().equals(")"))){
                resultText.deleteCharAt(resultText.length()-2);
                textInput.setText(resultText);
            }
            parser = new Parser(textInput.getText().toString());
            textOutput.setText(String.valueOf((parser.getText())));
        } else {
            Toast.makeText(this, "function is not correct", Toast.LENGTH_LONG).show();
        }
    }

    public void delete(View view) {
        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        resultText.delete(0, resultText.length());
        countBracket = 0;

        textInput.setText(resultText);
        textOutput.setText(tmpText = "");
    }

    public void endCap(View view) {
        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        Toast.makeText(this, "function is under development", Toast.LENGTH_LONG).show();
    }


    public void clickButton(View view) {
        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        Button button = (Button) view;
        String str = button.getText().toString();
        String strLine = "";
        if(resultText.length() != 0)
            strLine = String.valueOf(resultText.charAt(resultText.length()-1));

        try {
            if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 10)
                in = "number";
        } catch (NumberFormatException e) {
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
                in = "operand";
            } else if (str.equals(".")){
                in = "point";
            } else if (str.equals("(")){
                in = "leftBracket";
            } else if (str.equals(")")){
                in = "rightBracket";
            } else {
                in = "zero";
            }
        }

        try {
            if (Integer.parseInt(strLine) > -1 && Integer.parseInt(strLine) < 10)
                inLine = "number";
        } catch (NumberFormatException e) {
            if (strLine.equals("+") || strLine.equals("-") || strLine.equals("*") || strLine.equals("/")){
                inLine = "operand";
            } else if (strLine.equals(".")){
                inLine = "point";
            } else if (strLine.equals("(")){
                inLine = "leftBracket";
            } else if (strLine.equals(")")){
                inLine = "rightBracket";
            } else {
                inLine = "zero";
            }
        }

        Log.d(MY_LOG, "in = " + in);
        Log.d(MY_LOG, "inLine = " + inLine);

        textOutput.setText(button.getText());

        if(arrayCondition(in, inLine)){
            resultText.append(button.getText().toString());
            textInput.setText(resultText);
        }
    }

    boolean arrayCondition (String in, String inLine){

        int z = 0;  // zero
        int o = 1;  // operand
        int n = 2;  // number
        int p = 3;  // point
        int lb = 4;  // left bracket
        int rb = 5;  // right bracket

        int inOut = 0;

        switch (in){
            case "zero":
                inOut = z;
                break;
            case "operand":
                inOut = o;
                break;
            case "number":
                inOut = n;
                break;
            case "point":
                inOut = p;
                break;
            case "leftBracket":
                inOut = lb;
                break;
            case "rightBracket":
                inOut = rb;
                break;
            default:
                // message about error
                break;
        }

        int lineOut = 0;
        switch (inLine){
            case "zero":
                lineOut = z;
                break;
            case "operand":
                lineOut = o;
                break;
            case "number":
                lineOut = n;
                break;
            case "point":
                lineOut = p;
                break;
            case "leftBracket":
                lineOut = lb;
                break;
            case "rightBracket":
                lineOut = rb;
                break;
            default:
                // message about error
                break;
        }

        boolean [][] arrayCondition = new boolean[6][6];
            arrayCondition[o][z] = true;  // on condition that only minus
            arrayCondition[o][o] = true;  // on condition *1
            arrayCondition[o][n] = true;
            arrayCondition[o][p] = false;
            arrayCondition[o][lb] = true;  // on condition that only minus
            arrayCondition[o][rb] = true;

            arrayCondition[n][z] = true;
            arrayCondition[n][o] = true;
            arrayCondition[n][n] = true;
            arrayCondition[n][p] = true;
            arrayCondition[n][lb] = true;
            arrayCondition[n][rb] = false;

            arrayCondition[p][z] = false;
            arrayCondition[p][o] = false;
            arrayCondition[p][n] = true;
            arrayCondition[p][p] = false;
            arrayCondition[p][lb] = false;
            arrayCondition[p][rb] = false;

            arrayCondition[lb][z] = true;
            arrayCondition[lb][o] = true;
            arrayCondition[lb][n] = false;
            arrayCondition[lb][p] = false;
            arrayCondition[lb][lb] = true;
            arrayCondition[lb][rb] = false;

            arrayCondition[rb][z] = false;
            arrayCondition[rb][o] = false;
            arrayCondition[rb][n] = true;
            arrayCondition[rb][p] = false;
            arrayCondition[rb][lb] = false;
            arrayCondition[rb][rb] = true;  // on condition *2

            return arrayCondition[inOut][lineOut];
    }
}