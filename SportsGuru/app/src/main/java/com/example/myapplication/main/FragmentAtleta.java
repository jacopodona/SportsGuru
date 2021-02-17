package com.example.myapplication.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class FragmentAtleta extends Fragment {

    private final int INDEX=1;
    private Button next,back;
    private TextInputEditText nome,cognome;
    private TextInputLayout nomeLayout,cognomeLayout;

    public FragmentAtleta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_atleta, container, false);

        next=v.findViewById(R.id.atleta_next);
        back=v.findViewById(R.id.atleta_back);
        nome=v.findViewById(R.id.atleta_textField_nome);
        cognome=v.findViewById(R.id.atleta_textField_cognome);
        nomeLayout=v.findViewById(R.id.atleta_textLayout_nome);
        cognomeLayout=v.findViewById(R.id.atleta_textLayout_cognome);

        setupBottoni();

        return v;
    }

    private void setupBottoni() {
        MainActivity activity= (MainActivity) getActivity();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(nome.getText()) && !TextUtils.isEmpty(cognome.getText())){
                    activity.next(INDEX);
                    activity.confermaAtleta(nome.getText().toString(),cognome.getText().toString());
                    clearErrors();
                }
                else{
                    if(TextUtils.isEmpty(nome.getText())){
                        nomeLayout.setError(getResources().getString(R.string.insert_name_error));
                    }
                    if(TextUtils.isEmpty(cognome.getText())){
                        cognomeLayout.setError(getResources().getString(R.string.insert_surname_error));
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditText();
                clearErrors();
                activity.previous(INDEX);
            }
        });
    }

    private void clearEditText() {
        nome.setText("");
        cognome.setText("");
    }

    private void clearErrors(){
        nomeLayout.setError(null);
        cognomeLayout.setError(null);
    }

}