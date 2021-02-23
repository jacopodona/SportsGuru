 package com.example.myapplication.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.example.myapplication.modules.Richiesta;
import com.example.myapplication.utilities.HttpRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONObject;

 public class PresentationActivity extends AppCompatActivity {

    private MaterialButton back;
    private MaterialTextView risposta;
    private String nome,cognome,stat,anno;
    private Richiesta richiesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        risposta=findViewById(R.id.presentation_response);

        Intent i=getIntent();

        richiesta= (Richiesta) i.getSerializableExtra("request");
        String URL="/player/date/"+richiesta.getStatistica()+"/"+richiesta.getCognome()+"%20"+richiesta.getNome().charAt(0)+".&"+richiesta.getData();

        new HttpRequest(this, URL, Request.Method.GET, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                risposta.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                risposta.setText(error.toString());
            }
        }).run();


        setupBottoni();
    }

    private void setupBottoni() {
        /*back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }
}