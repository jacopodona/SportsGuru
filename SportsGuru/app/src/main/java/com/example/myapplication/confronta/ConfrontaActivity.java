package com.example.myapplication.confronta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.R;
import com.example.myapplication.modules.Richiesta;
import com.example.myapplication.utilities.HttpRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfrontaActivity extends AppCompatActivity {

    private ImageView close;
    private MaterialButton cambia;
    private int valstat1,valstat2;
    private MaterialTextView nome1,nome2,val1,val2,confronto;
    private ProgressBar progressBar;
    private Richiesta atleta1,atleta2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confronta);

        Intent i=getIntent();
        atleta1= (Richiesta) i.getSerializableExtra("atleta1");
        atleta2= (Richiesta) i.getSerializableExtra("atleta2");

        close=findViewById(R.id.compare_close);
        cambia=findViewById(R.id.compare_cambia);
        nome1=findViewById(R.id.compare_athlete1);
        nome2=findViewById(R.id.compare_athlete2);
        val1=findViewById(R.id.compare_stat_athlete1);
        val2=findViewById(R.id.compare_stat_athlete2);
        confronto=findViewById(R.id.compare_textview);
        progressBar=findViewById(R.id.compare_progressbar);

        nome1.setText(atleta1.getNome().charAt(0)+". "+atleta1.getCognome());
        nome2.setText(atleta2.getNome().charAt(0)+". "+atleta2.getCognome());

        val1.setTypeface(val1.getTypeface(), Typeface.BOLD);
        val2.setTypeface(val2.getTypeface(), Typeface.BOLD);


        setupBottoni();
        setupConfronto();

    }

    private void setupConfronto() {
        String URL1="/player/date/"+atleta1.getStatistica()+"/"+atleta1.getCognome()+"%20"+atleta1.getNome().charAt(0)+".&"+atleta1.getData();

        new HttpRequest(this, URL1, Request.Method.GET, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                valstat1=extractValue(response.toString());
                val1.setText(""+valstat1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Errore in confronto1",error.toString());
            }
        }).run();
        String URL2="/player/date/"+atleta2.getStatistica()+"/"+atleta2.getCognome()+"%20"+atleta2.getNome().charAt(0)+".&"+atleta2.getData();

        new HttpRequest(this, URL2, Request.Method.GET, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                valstat2=extractValue(response.toString());
                val2.setText(""+valstat2);
                setupTextView();
                setupProgressBar();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Errore in confronto2",error.toString());
            }
        }).run();
    }

    private void setupTextView() {
        if(valstat1>valstat2){
            confronto.setText(atleta1.getNome()+" "+atleta1.getCognome()+" ha "+(valstat1-valstat2)+" "+atleta1.getStatistica()+" in più rispetto a "+atleta2.getNome()+" "+atleta2.getCognome());
        }
        else if(valstat2>valstat1){
            confronto.setText(atleta1.getNome()+" "+atleta1.getCognome()+" ha "+(valstat1-valstat2)+" "+atleta1.getStatistica()+" in meno rispetto a "+atleta2.getNome()+" "+atleta2.getCognome());
        }
        else if(valstat1==valstat2){
            confronto.setText(atleta1.getNome()+" "+atleta1.getCognome()+" e "+atleta2.getNome()+" "+atleta2.getCognome()+" hanno lo stesso numero di "+atleta1.getStatistica());
        }
    }

    private void setupProgressBar() {
        double max=valstat1+valstat2;
        double percentuale=valstat1/max;
        percentuale=percentuale*100;
        Log.d("Details",valstat1+"+"+valstat2+"="+max);
        Log.d("Percentuale",percentuale+"%");
        int progress= (int) percentuale;
        progressBar.setProgress(progress);
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
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cambia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambia();
            }
        });
    }

    private void cambia() {

    }
}