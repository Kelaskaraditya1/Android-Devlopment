package com.starkindustries.volleylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.starkindustries.volleylibrary.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
public ActivityMainBinding binding;
    String url="https://www.google.com";
    public String api_url="https://jsonplaceholder.typicode.com/todos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("Content","Content Parsed From Url is: "+response.substring(0,500));
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Content","Failed to Parse Data from Url");
//            }
//        });
//        queue.add(request);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, api_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        Log.d("Content","The Title of the Book is "+object.getString("title")+"\n");
                        Log.d("Content","The Status wheather book is completed or not is "+object.getBoolean("completed")+"\n");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Content","Failed to Parse data from Url");
            }
        });
        queue.add(request);
    }
}