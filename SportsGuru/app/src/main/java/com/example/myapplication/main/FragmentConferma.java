package com.example.myapplication.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.modules.Richiesta;
import com.google.android.material.textview.MaterialTextView;


public class FragmentConferma extends Fragment {

    private final int INDEX=4;
    private Button confirm,back;
    private MainActivity activity;
    private Richiesta richiesta;
    private MaterialTextView atleta,statistica,data;

    public FragmentConferma() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_conferma, container, false);

        activity= (MainActivity) getActivity();
        richiesta=activity.getRichiesta();

        atleta=v.findViewById(R.id.conferma_textview_atleta);
        statistica=v.findViewById(R.id.conferma_textview_stat);
        data=v.findViewById(R.id.conferma_textview_data);
        confirm=v.findViewById(R.id.conferma_confirm);
        back=v.findViewById(R.id.conferma_back);

        updateTextFields();

        setupBottoni();

        return v;
    }

    private void updateTextFields() {
        richiesta=activity.getRichiesta();
        atleta.setText(richiesta.getNome()+" "+richiesta.getCognome());
        statistica.setText(richiesta.getStatistica());
        data.setText(richiesta.getData());
    }

    private void setupBottoni() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.research();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.previous(INDEX);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTextFields();
    }
}