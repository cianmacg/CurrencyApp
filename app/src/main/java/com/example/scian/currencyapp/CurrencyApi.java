package com.example.scian.currencyapp;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by scian on 25/02/2018.
 */

public class CurrencyApi {

    String currentRate = "nun";
    Context c;

    public CurrencyApi() {
    }

    public void request(Context c) {
        RequestQueue queue = Volley.newRequestQueue(c);
        String url ="http://data.fixer.io/api/latest?access_key="+R.string.currency_api_key+"&symbols=JPY";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String s = "chance";
                try{

                    s = response.getJSONObject("rates").get("JPY").toString();
                } catch(Exception e){
                    System.out.println("Something went wrong.");
                }
                currentRate = s;
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });

        queue.add(jsonRequest);
    }
}
