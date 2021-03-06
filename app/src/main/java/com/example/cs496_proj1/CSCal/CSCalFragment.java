package com.example.cs496_proj1.CSCal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cs496_proj1.R;


public class CSCalFragment extends Fragment {

    public CSCalFragment() {
    }

    public static CSCalFragment newInstance() {
        CSCalFragment fragment = new CSCalFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_c_s_cal, container, false);
        Button button1 = view.findViewById(R.id.button);
        Button button2 = view.findViewById(R.id.button2);

        button1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NumSystem.class);
            startActivity(intent);
        });

        button2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BitOperation.class);
            startActivity(intent);
        });

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
