package com.example.myapplication.welcome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.main.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.utilities.HttpRequest;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

public class WelcomeFragment extends Fragment {

    private MaterialButton iniziamo;
    private final int INDEX=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_welcome, container, false);

        /*new HttpRequest(getContext(),"/ranking/teams/20182019", Request.Method.GET,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Richiesta Effettuata", response.toString());
            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Richiesta bloccata", error.toString());
                    }
                }).run();*/

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