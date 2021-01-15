 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.textview.MaterialTextView;

public class PresentationActivity extends AppCompatActivity {

    MaterialTextView nomeAtleta,statAtleta,annoAtleta;
    String nome,cognome,stat,anno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        nomeAtleta=findViewById(R.id.presentation_textview_atleta);
        statAtleta=findViewById(R.id.presentation_textview_stat);
        annoAtleta=findViewById(R.id.presentation_textview_anno);

        Intent i=getIntent();
        nome=i.getStringExtra("nome");
        cognome=i.getStringExtra("cognome");
        stat=i.getStringExtra("statistica");
        anno=i.getStringExtra("anno");

        Log.d("Request","Atleta: "+nome+" "+cognome+"\nStatistica: "+stat+"\nAnno: "+anno);

        nomeAtleta.setText(nome+" "+cognome);
        statAtleta.setText(stat);
        annoAtleta.setText(anno);
    }
}