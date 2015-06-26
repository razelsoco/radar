package com.radar.app.network;

import android.util.Log;


import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.Map;

/**
 * Created by razelsoco on 25/6/15.
 */
public class RequestHandler {
    public static final String REGISTER_URL = "";
    public static final String UPDATE_DEVICE_URL = "";
    public static final String GET_DEVICES_LIST_URL = "";
    public static final String GET_DEVICE_DETAIL_URL = "";
    public static final String ADD_DEVICE_NOTE_URL = "";
    public interface ResponseListener<T> extends Response.ErrorListener{
        public void onErrorResponse(VolleyError volleyError);
        public void onResponse(T response);
    }

    public static void registerDevice(){

    }

    public static void updateDeviceStatus(){

    }
}
