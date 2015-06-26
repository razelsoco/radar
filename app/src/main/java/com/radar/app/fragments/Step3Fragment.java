package com.radar.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.radar.app.MainActivity;
import com.radar.app.R;
import com.radar.app.helper.ConstHelper;
import com.radar.app.helper.PreferenceHelper;
import com.radar.app.helper.Utils;
import com.radar.app.network.RequestHandler;

/**
 * Created by razelsoco on 26/6/15.
 */
public class Step3Fragment extends BaseFragment implements View.OnClickListener {
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
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    });

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
}
