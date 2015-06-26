package com.radar.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by razelsoco on 25/6/15.
 */
public class RadarApplication extends Application {

    private static RadarApplication appInstance;
    public SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    }

    public static RadarApplication getInstance(){
        return appInstance;
    }
}
