package com.radar.app.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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
        int interval = 10000;

        // Retrieve a PendingIntent that will perform a broadcast
        Intent alarmIntent = new Intent(c, UpdateTaskAlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c, 0, alarmIntent, 0);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(c, "Alarm Set", Toast.LENGTH_SHORT).show();
    }
}
