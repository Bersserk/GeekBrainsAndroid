package com.example.gbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.CharArrayReader;
import java.util.function.IntUnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public final static String MY_LOG = "myLog";
    StringBuilder resultText;
    String tmpText = "";
    Boolean flag = false;


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

    public void result (View view) {

        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        parser = new Parser(textInput.getText().toString());
        textOutput.setText(String.valueOf((parser.getText())));

    }

    public void num(View view) {

        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        Button button = (Button) view;

        if (!button.getText().equals(".")) {
            tmpText = tmpText.concat(button.getText().toString());

            resultText.append(button.getText());

            textInput.setText(resultText);
            textOutput.setText(tmpText);
        } else if (!tmpText.equals("") && !flag){
            tmpText = tmpText.concat(button.getText().toString());
            resultText.append(button.getText());

            flag = true;
            textInput.setText(resultText);
            textOutput.setText(tmpText);
        }
    }

    public void operand(View view) {
        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        //resultText.append(tmpText);
        tmpText = "";
        flag = false;

        Button button = (Button) view;

        resultText.append(" " + button.getText().toString() + " ");
        textOutput.setText(button.getText());

        textInput.setText(resultText);
    }

    public void delete(View view) {
        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        //resultText.append(tmpText);
        tmpText = "";
        flag = false;
        resultText.delete(0, resultText.length());

        textInput.setText(resultText);
    }

    public void endCap(View view) {
        Log.d(MY_LOG, new Object() {
        }.getClass().getEnclosingMethod().getName());

        Toast.makeText(this, "function is under development", Toast.LENGTH_LONG).show();
    }



}