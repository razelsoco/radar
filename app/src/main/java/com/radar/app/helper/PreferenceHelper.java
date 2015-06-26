package com.radar.app.helper;

import com.radar.app.RadarApplication;

/**
 * Created by razelsoco on 25/6/15.
 */
public class PreferenceHelper {
    public static final String KEY_SENT_TOKEN_TO_SERVER="token_sent_to_server";
    public static final String KEY_DEVICE_TYPE="device_type";
    public static final String KEY_USERNAME="username";
    public static final String KEY_DEVICE_NAME="device_name";
    public static void setKeyDeviceType(String type){
        RadarApplication.getInstance().sharedPreferences.edit().putString(KEY_DEVICE_TYPE,type).commit();
    }

    public static String getKeyDeviceType(){
        return RadarApplication.getInstance().sharedPreferences.getString(KEY_DEVICE_TYPE,ConstHelper.DEVICE_TYPE_COMPANY);
    }

    public static void setUsername(String type){
        RadarApplication.getInstance().sharedPreferences.edit().putString(KEY_USERNAME,type).commit();
    }

    public static String getUsername(){
        return RadarApplication.getInstance().sharedPreferences.getString(KEY_USERNAME,null);
    }

    public static void setDevicename(String type){
        RadarApplication.getInstance().sharedPreferences.edit().putString(KEY_DEVICE_NAME,type).commit();
    }

    public static String getDevicename(){
        return RadarApplication.getInstance().sharedPreferences.getString(KEY_DEVICE_NAME,null);
    }


}
