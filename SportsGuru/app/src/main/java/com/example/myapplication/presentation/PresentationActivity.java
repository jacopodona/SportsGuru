 package com.example.myapplication.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.example.myapplication.confronta.ConfrontaActivity;
import com.example.myapplication.main.MainActivity;
import com.example.myapplication.modules.Richiesta;
import com.example.myapplication.utilities.HttpRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

 public class PresentationActivity extends AppCompatActivity {

    private MaterialButton nuovaRicerca,confronta;
    private MaterialTextView risposta;
    private AlertDialog confronto;
    private AlertDialog.Builder confrontoBuilder;
    private EditText nomeAtleta2,cognomeAtleta2;
    private String nome,cognome,stat,anno;
    private int valoreAtleta1,valoreAtleta2;
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
        String data;
        if(richiesta.getData().equals("In Carriera")){
            Calendar cal=Calendar.getInstance();
            SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");
            cal.set(Calendar.YEAR,1);
            Date date = cal.getTime();
            data=formatter.format(date);
        }
        else{
            data=richiesta.getData();
        }
        String URL="/player/date/"+richiesta.getStatistica()+"/"+richiesta.getCognome()+"%20"+richiesta.getNome().charAt(0)+".&"+data;

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


        buildDialog();
        setupBottoni();
    }

     private String handleResponse(int statValue) {
        String result=null;
        if(statValue>=0){
            valoreAtleta1=statValue;
            if(richiesta.getData().equals("In Carriera")){
                result=richiesta.getData()+" il giocatore "+richiesta.getNome()+" "+richiesta.getCognome()+" ha effettuato "+statValue+" "+richiesta.getStatistica();
            }
            else{
                result="Dal "+richiesta.getData()+" il giocatore "+richiesta.getNome()+" "+richiesta.getCognome()+" ha effettuato "+statValue+" "+richiesta.getStatistica();
            }
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
        confronta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confronto.show();
            }
        });
    }

     private void confronta(String nomeAtleta2, String cognomeAtleta2) {
         buildDialog();
         Intent i=new Intent(getApplicationContext(), ConfrontaActivity.class);
         i.putExtra("atleta1",richiesta);
         i.putExtra("atleta2",new Richiesta(nomeAtleta2,cognomeAtleta2,richiesta.getStatistica(),richiesta.getData()));
         startActivity(i);
     }

     private void buildDialog() {
         confrontoBuilder= new AlertDialog.Builder(this);
         View mView=getLayoutInflater().inflate(R.layout.dialog_compare,null);
         confrontoBuilder.setTitle("Confronta con un altro atleta");
         nomeAtleta2=mView.findViewById(R.id.dialog_nome_confronto);
         cognomeAtleta2=mView.findViewById(R.id.dialog_cognome_confronto);
         confrontoBuilder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 if(!TextUtils.isEmpty(nomeAtleta2.getText()) && !TextUtils.isEmpty(cognomeAtleta2.getText())){
                     confronta(nomeAtleta2.getText().toString(),cognomeAtleta2.getText().toString());
                 }
             }
         });

         confrontoBuilder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });
         confrontoBuilder.setView(mView);
         confronto=confrontoBuilder.create();
     }

     private void newResearch() {
         Intent i=new Intent(getApplicationContext(), MainActivity.class);
         startActivity(i);
         finish();
     }
 }