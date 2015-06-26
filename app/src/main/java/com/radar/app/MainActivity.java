package com.radar.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.radar.app.adapter.DeviceListAdapter;
import com.radar.app.gcm.RegistrationIntentService;
import com.radar.app.helper.Utils;
import com.radar.app.models.Device;
import com.radar.app.models.DeviceListResponse;
import com.radar.app.network.RequestHandler;
import com.radar.app.receiver.UpdateTaskAlarmReceiver;


public class MainActivity extends FragmentActivity {
    private ListView lvDevices;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    DeviceListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        findViewById(R.id.tv_ring_device).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
//                r.play();
//            }
//        });
        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.action_bar);
        lvDevices = (ListView) findViewById(R.id.lv_device_list);
        lvDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Device device = mAdapter.getItem(position);

            }
        });
        RequestHandler.getDeviceList(new RequestHandler.ResponseListener<DeviceListResponse>() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,"An error occured",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(DeviceListResponse response) {
                if(response.getStatus().equals("1")){
                    mAdapter = new DeviceListAdapter(MainActivity.this, response.getDevices());
                    lvDevices.setAdapter(mAdapter);
                }
            }
        }, Utils.getDeviceId(MainActivity.this), Utils.getssId(MainActivity.this));


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
