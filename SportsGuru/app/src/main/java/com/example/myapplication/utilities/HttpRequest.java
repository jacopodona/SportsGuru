package com.example.myapplication.utilities;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    final String serverURL = "http://10.0.2.2:4000";//localhost computer
    private JsonObjectRequest request;
    private RequestQueue queue;

    public HttpRequest(Context context, String partialUrl, int method, JSONObject body, Response.Listener<JSONObject> onResponse, Response.ErrorListener onError) {
        request = new JsonObjectRequest(method, serverURL + partialUrl, body, onResponse, onError) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type", "application/json");

                return params;
            }
        };
        queue = Volley.newRequestQueue(context);
    }

    public HttpRequest(Context context, String partialUrl, int method, Response.Listener<JSONObject> onResponse, Response.ErrorListener onError) {
        this(context,partialUrl,method,null,onResponse,onError);
    }

    public void run() {
            queue.add(request);
    }
}
