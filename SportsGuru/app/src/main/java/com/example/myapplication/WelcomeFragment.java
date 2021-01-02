package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class WelcomeFragment extends Fragment {

    private MaterialButton iniziamo;
    private final int INDEX=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_welcome, container, false);

        iniziamo=v.findViewById(R.id.welcome_iniziamo);
        iniziamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity a= (MainActivity) getActivity();
                a.next(INDEX);
            }
        });

        return v;
    }
}