package com.example.cs496_proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Contact List Initialization
        //ArrayAdapter adapter = new ArrayAdater(getActivity(), R.id.aaa, LIST_MENU);
        //ListView listview = (ListView) findViewById(R.id.contacts);
        //listview.setAdapter(adapter);
    }

    static final String[] LIST_MENU = {"NAME", "NUMBER", "MEMO"} ;

}