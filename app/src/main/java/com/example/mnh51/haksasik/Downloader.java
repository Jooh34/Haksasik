package com.example.mnh51.haksasik;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mnh51.haksasik.MainActivity.UpdateCallback;

/**
 * Created by mnh51 on 2018-04-13.
 */

public class Downloader {
    private static final String SERVER_URL = "http://34.213.112.175";

    private static final String ROUTE_MENU = "/menus";
    private static final String ROUTE_DATE = "/dates";

    private static final int REQUEST_MENU = 100;
    private static final int REQUEST_DATE = 101;

    RequestQueue queue;

    String menuJSON;
    String dateJSON;

    public Downloader(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void updateData(final UpdateCallback updateCallback) {

        downloadData(REQUEST_MENU, new VolleyCallback(){
            @Override
            public void onSuccess(String response) {
                updateCallback.onSuccess(response, REQUEST_MENU);
            }

            @Override
            public void onFailure() {
                updateCallback.onFailure();
            }
        });

        downloadData(REQUEST_DATE, new VolleyCallback(){
            @Override
            public void onSuccess(String response) {
                updateCallback.onSuccess(response, REQUEST_DATE);
            }

            @Override
            public void onFailure() {
                updateCallback.onFailure();
            }
        });
    }

    public void downloadData(int requestType, final VolleyCallback volleyCallback) {
        String url = "";

        if(requestType == REQUEST_MENU) {
            url = SERVER_URL + ROUTE_MENU;
        } else if(requestType == REQUEST_DATE) {
            url = SERVER_URL + ROUTE_DATE;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        volleyCallback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error
                Log.e("err", error.toString());
                volleyCallback.onFailure();
            }
        });

        queue.add(stringRequest);
    }

    private interface VolleyCallback {
        void onSuccess(String response);
        void onFailure();
    }
}
