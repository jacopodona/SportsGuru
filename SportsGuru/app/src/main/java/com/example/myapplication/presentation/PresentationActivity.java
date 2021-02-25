 package com.example.myapplication.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.example.myapplication.main.MainActivity;
import com.example.myapplication.modules.Richiesta;
import com.example.myapplication.utilities.HttpRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

 public class PresentationActivity extends AppCompatActivity {

    private MaterialButton nuovaRicerca,confronta;
    private MaterialTextView risposta;
    private String nome,cognome,stat,anno;
    private Richiesta richiesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        risposta=findViewById(R.id.presentation_response);
        nuovaRicerca=findViewById(R.id.presentation_nuovaricerca);
        confronta=findViewById(R.id.presentation_confronta);

        Intent i=getIntent();

        richiesta= (Richiesta) i.getSerializableExtra("request");
        String URL="/player/date/"+richiesta.getStatistica()+"/"+richiesta.getCognome()+"%20"+richiesta.getNome().charAt(0)+".&"+richiesta.getData();

        new HttpRequest(this, URL, Request.Method.GET, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                risposta.setText(handleResponse(extractValue(response.toString())));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                risposta.setText(error.toString());
            }
        }).run();


        setupBottoni();
    }

     private String handleResponse(int statValue) {
        String result=null;
        if(statValue>=0){
            result="Dal "+richiesta.getData()+" il giocatore "+richiesta.getNome()+" "+richiesta.getCognome()+" ha effettuato "+statValue+" "+richiesta.getStatistica();
        }else{
            result="C'è stato un errore nella formazione della richiesta";
        }
        return result;
     }

     private int extractValue(String jsonObject) {
        int value=-1;
        JSONObject json= null;
        try {
            json = new JSONObject(jsonObject);
            if(json.getString("message").equals("Success")){
                JSONObject result=json.getJSONObject("data");
                value=result.getInt("value");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }

     private void setupBottoni() {
        nuovaRicerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newResearch();
            }
        });
    }

     private void newResearch() {
         Intent i=new Intent(getApplicationContext(), MainActivity.class);
         startActivity(i);
         finish();
     }
 }