package com.radar.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.radar.app.fragments.Step1Fragment;
import com.radar.app.gcm.RegistrationIntentService;

/**
 * Created by razelsoco on 25/6/15.
 */
public class OnboardingActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content_frame,new Step1Fragment(),Step1Fragment.class.getName());
        fragmentTransaction.commit();

       // if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        //}
    }
}
