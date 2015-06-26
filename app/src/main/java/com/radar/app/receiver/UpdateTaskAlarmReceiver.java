package com.radar.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.radar.app.helper.Utils;
import com.radar.app.network.RequestHandler;

/**
 * Created by razelsoco on 25/6/15.
 */
public class UpdateTaskAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()!= null && intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
            Utils.scheduleAlarm(context);
        }else {
            //Toast.makeText(context, "Alarm Received", Toast.LENGTH_SHORT).show();
            RequestHandler.updateDeviceStatus(null, Utils.getDeviceId(context), Utils.getssId(context));
        }
    }
}
