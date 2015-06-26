package com.radar.app.network;

import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.radar.app.RadarApplication;
import com.radar.app.helper.PreferenceHelper;
import com.radar.app.helper.Utils;
import com.radar.app.models.DeviceDetailResponse;
import com.radar.app.models.DeviceListResponse;
import com.radar.app.models.RadarResponse;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by razelsoco on 25/6/15.
 */
public class RequestHandler {
    public static final String REGISTER_URL = "/register_phone.php";
    public static final String UPDATE_DEVICE_URL = "/update_phone_status.php";
    public static final String PING_DEVICE_URL = "/ping_device.php";
    public static final String GET_DEVICES_LIST_URL = "/device_list.php";
    public static final String GET_DEVICE_DETAIL_URL = "/device_details.php";
    public static final String ADD_DEVICE_NOTE_URL = "/add_malfunction_log.php";
    public interface ResponseListener<T> extends Response.ErrorListener{
        public void onErrorResponse(VolleyError volleyError);
        public void onResponse(T response);
    }

    public static void registerDevice(ResponseListener listener,final String formFactor,final String res, final String size,final  String deviceId){
        GsonRequest<RadarResponse> request = new GsonRequest<RadarResponse>(Request.Method.POST, REGISTER_URL, RadarResponse.class, listener ) {
            @Override
            public Map<String, String> getCustomParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("title", PreferenceHelper.getDevicename());
                params.put("os_version", Build.VERSION.CODENAME+" "+Build.VERSION.RELEASE);
                params.put("form_factor",formFactor);
                params.put("screen_resolution",res);
                params.put("screen_size",size);
                params.put("unique_key", deviceId);
                params.put("gcm_key","somegcmkey");
                return params;
            }
        };

        MyVolleySingleton.getInstance(RadarApplication.getInstance().getApplicationContext()).addToRequestQueue(request);

    }

    public static void updateDeviceStatus(ResponseListener listener, final String deviceID, final String ssId){
        GsonRequest<RadarResponse> request = new GsonRequest<RadarResponse>(Request.Method.POST, UPDATE_DEVICE_URL, RadarResponse.class, listener ) {
            @Override
            public Map<String, String> getCustomParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("unique_key", deviceID);
                params.put("wifi_ssid",ssId);
//                params.put("longitude","Phone");
//                params.put("latitude","1024x786");
                return params;
            }
        };

        MyVolleySingleton.getInstance(RadarApplication.getInstance().getApplicationContext()).addToRequestQueue(request);
    }

    public static void addDeviceLog(ResponseListener listener, String deviceID, String authorName, String comment){
        GsonRequest<RadarResponse> request = new GsonRequest<RadarResponse>(Request.Method.POST, ADD_DEVICE_NOTE_URL, RadarResponse.class, listener ) {
            @Override
            public Map<String, String> getCustomParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("unique_key","device1");
                params.put("author_name","umair");
                params.put("comment","sample comment");
                return params;
            }
        };

        MyVolleySingleton.getInstance(RadarApplication.getInstance().getApplicationContext()).addToRequestQueue(request);
    }

    public static void sendPing(ResponseListener listener, String deviceID, String name,String msg, String action){
        GsonRequest<RadarResponse> request = new GsonRequest<RadarResponse>(Request.Method.POST, PING_DEVICE_URL, RadarResponse.class, listener ) {
            @Override
            public Map<String, String> getCustomParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("unique_key","device1");
                params.put("name","umair");
                params.put("message","sample comment");
                params.put("action","leav it");
                return params;
            }
        };

        MyVolleySingleton.getInstance(RadarApplication.getInstance().getApplicationContext()).addToRequestQueue(request);
    }

    public static void getDeviceDetail(ResponseListener listener,final String deviceID,final String ssId){
        GsonRequest<DeviceDetailResponse> request = new GsonRequest<DeviceDetailResponse>(Request.Method.POST, GET_DEVICE_DETAIL_URL, DeviceDetailResponse.class, listener ) {
            @Override
            public Map<String, String> getCustomParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("unique_key",deviceID);
                params.put("wifi_ssid",ssId);
                return params;
            }
        };

        MyVolleySingleton.getInstance(RadarApplication.getInstance().getApplicationContext()).addToRequestQueue(request);
    }

    public static void getDeviceList(ResponseListener listener,final String deviceID,final String ssId){
        GsonRequest<DeviceListResponse> request = new GsonRequest<DeviceListResponse>(Request.Method.POST, GET_DEVICES_LIST_URL, DeviceListResponse.class, listener ) {
            @Override
            public Map<String, String> getCustomParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("unique_key",deviceID);
                params.put("wifi_ssid",ssId);
                return params;
            }
        };

        MyVolleySingleton.getInstance(RadarApplication.getInstance().getApplicationContext()).addToRequestQueue(request);
    }
}
