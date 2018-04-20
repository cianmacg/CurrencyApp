package com.example.scian.currencyapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    CurrencyApi cApi = new CurrencyApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mTextView = findViewById(R.id.text);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

        // The id of the channel.
        String id = "my_channel_01";



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(id, name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel);
        }
    }

    /*public void search(View v){
//        String s = apiRequest();
//        mTextView.setText("Response: "+s);
    }

    public String apiRequest(View v) {
        final NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

        // The id of the channel.
        final String id = "my_channel_01";

        final  NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, id)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Notification Title")
                .setContentText("Notification Content Text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
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
                mBuilder.setContentTitle("Exchange Rate!")
                        .setContentText(s);
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
    return null;

    }*/

    public void startNotification(View v){
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_DAY, pendingIntent);

    }

}
