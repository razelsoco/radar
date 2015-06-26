package com.radar.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.radar.app.MainActivity;
import com.radar.app.R;
import com.radar.app.gcm.RegistrationIntentService;
import com.radar.app.helper.ConstHelper;
import com.radar.app.helper.PreferenceHelper;
import com.radar.app.helper.Utils;
import com.radar.app.network.RequestHandler;

/**
 * Created by razelsoco on 26/6/15.
 */
public class Step3Fragment extends BaseFragment implements View.OnClickListener {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup3,container,false);

        view.findViewById(R.id.iv_back).setOnClickListener(this);
        view.findViewById(R.id.bt_start).setOnClickListener(this);
        view.findViewById(R.id.iv_next).setVisibility(View.INVISIBLE);
        ((TextView)view.findViewById(R.id.tv_step)).setText(getString(R.string.step3));
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                popbackstack();
                break;
            case R.id.bt_start:
                if(PreferenceHelper.getKeyDeviceType().equals(ConstHelper.DEVICE_TYPE_COMPANY)){
                    showProgressDialog("Registering device...");
                    RequestHandler.registerDevice(new RequestHandler.ResponseListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.d("", "onErrorResponse");
                            dismissProgressDialog();
                        }

                        @Override
                        public void onResponse(Object response) {
                            Log.d("", "onResponse");
                            dismissProgressDialog();
                            Utils.scheduleAlarm(getActivity());
                             if (checkPlayServices()) {
                                 //Start IntentService to register this application with GCM.
                                 Intent intent = new Intent(getActivity(), RegistrationIntentService.class);
                                 getActivity().startService(intent);
                             }
                            PreferenceHelper.setSetUpFinished();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }, Utils.getFormFactor(getActivity()), Utils.getDimension(getActivity()), "", Utils.getDeviceId(getActivity()));

                }else{
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                break;
            default:
                break;

        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("", "This device is not supported.");
                getActivity().finish();
            }
            return false;
        }
        return true;
    }
}
