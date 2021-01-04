package com.example.cs496_proj1.CSCal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.widget.Toast;

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

                else if (dec.getBytes().length<=0 && hexa.getBytes().length<=0){
                    if (isBinary(bin)) {
                        Binclicker();
                    }
                    else{
                        Toast msg = Toast.makeText(getApplicationContext(), "이진수만 입력해주세요", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                }

                else if (dec.getBytes().length<=0 && bin.getBytes().length<=0) {
                    if (isHex(hexa)) {
                        Hexclicker();
                    }
                    else {
                        Toast msg = Toast.makeText(getApplicationContext(), "16진수만 입력해주세요", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                }

                else{
                    Toast mytoast = Toast.makeText(getApplicationContext(), "세 칸 중 한 곳에만 숫자를 입력해주세요", Toast.LENGTH_LONG);
                    mytoast.show();
                }

            }
        });

        // Clear
        Button clearbutton = (Button) findViewById(R.id.button4);
        clearbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                decimal.setText(null);
                binary.setText(null);
                hex.setText(null);
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

    public boolean isBinary(String bin){
        int num = Integer.parseInt(bin);
        while (num != 0){
            if (num % 10 > 1){
                return false;
            }
            num = num / 10;
        }
        return true;
    }

    public boolean isHex(String hex) {
        int n = hex.length();
        String hexdigit = "0123456789ABCDEFabcdef";
        for (int i = 0; i < n; i++) {
            char ch = hex.charAt(i);
            if (hexdigit.indexOf(ch) == -1) return false;
        }
        return true;
    }

}
