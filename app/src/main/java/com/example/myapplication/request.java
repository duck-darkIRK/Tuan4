package com.example.myapplication;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class request {
    String strJson = "";
    public void  getJsonArrayOfObject(Context contexts, TextView textView) {
        RequestQueue queue = Volley.newRequestQueue(contexts);
        //192.168.31.101
        String url = "http://172.16.6.76:3000/a.json";

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        strJson = "";
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject person = response.getJSONObject(i);
                                String id = person.getString("id");
                                String name = person.getString("name");
                                String email = person.getString("email");

                                strJson += "Id: " + id  + "\n";
                                strJson += "Name: " + name  + "\n";
                                strJson += "Email: " + email  + "\n";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        textView.setText(strJson);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.getMessage());
                Log.d("error", error.getMessage());
            }
        }
        );
        queue.add(request);
    }
}
