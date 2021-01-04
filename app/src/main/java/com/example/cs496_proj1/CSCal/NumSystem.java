package com.example.cs496_proj1.CSCal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import com.example.cs496_proj1.R;

public class NumSystem extends AppCompatActivity  {

    EditText decimal;
    EditText binary;
    EditText hex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_system);

        decimal = findViewById(R.id.decimal);
        binary = findViewById(R.id.binary);
        hex = findViewById(R.id.hex);

        // Get results
        Button resultbutton = (Button) findViewById(R.id.button3);
        resultbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dec = decimal.getText().toString();
                String bin = binary.getText().toString();
                String hexa = hex.getText().toString();

                if (bin.getBytes().length<=0 && hexa.getBytes().length<=0){
                    Decclicker();
                }

                if (dec.getBytes().length<=0 && hexa.getBytes().length<=0){
                    Binclicker();
                }

                if (dec.getBytes().length<=0 && bin.getBytes().length<=0){
                    Hexclicker();
                }
            }
        });
    }

    public void Decclicker(){
        String strdec = decimal.getText().toString();
        int num = Integer.parseInt(strdec);
        String dectobin = Integer.toBinaryString(num);
        String dectohex = Integer.toHexString(num);
        binary.setText(dectobin);
        hex.setText(dectohex);
    }

    public void Binclicker(){
        String strbin = binary.getText().toString();
        int num = Integer.parseInt(strbin, 2);
        String bintodec = Integer.toString(num);
        String bintohex = Integer.toHexString(num);
        decimal.setText(bintodec);
        hex.setText(bintohex);
    }

    public void Hexclicker(){
        String strhex = hex.getText().toString();
        int num = Integer.parseInt(strhex, 16);
        String hextodec = Integer.toString(num);
        String hextobin = Integer.toBinaryString(num);
        decimal.setText(hextodec);
        binary.setText(hextobin);
    }


}