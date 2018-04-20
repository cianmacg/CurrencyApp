package com.example.scian.currencyapp;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Notification_receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        // The id of the channel.
        final String id = "my_channel_01";

        final  NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, id)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Notification Title")
                .setContentText("Notification Content Text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String api_endpoint ="http://data.fixer.io/api/latest?access_key=cbb35e1c9eb979f969da51a5b211c2a5&symbols=JPY";

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, api_endpoint, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                String s = "err!";
                try{
                    s = response.getJSONObject("rates").get("JPY").toString();

                } catch(Exception e){
                    System.out.println("Something went wrong.");
                }
                mBuilder.setContentTitle("Euro to Yen rate")
                        .setContentText("€1 = ¥"+s);
                notificationManager.notify(0, mBuilder.build());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                mBuilder.setContentTitle("Exchange Rate!")
                        .setContentText("Something went wrong!");
                notificationManager.notify(0, mBuilder.build());

            }
        });

        queue.add(jsonRequest);
    }
}
