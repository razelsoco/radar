package com.radar.app.helper;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.Toast;

import com.radar.app.R;
import com.radar.app.receiver.UpdateTaskAlarmReceiver;

/**
 * Created by razelsoco on 25/6/15.
 */
public class Utils {

    /**
     * Schedules the update device status task for background updating
     * */
    public static void scheduleAlarm(Context c) {
        AlarmManager manager = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        int interval = 3600000; //1 hour

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(c, UpdateTaskAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c, 0, alarmIntent, 0);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        //Toast.makeText(c, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public static String getDeviceId(Context c){
        TelephonyManager mngr = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
        return mngr.getDeviceId();
    }

    public static String getssId(Context c){
        WifiManager wifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getSSID();
    }

    public static String getDimension(FragmentActivity c){
        DisplayMetrics metrics = new DisplayMetrics();
        c.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels+" x "+metrics.heightPixels;
    }

    public static String getFormFactor(Context c){
        return c.getString(R.string.form_factor);
    }
}
