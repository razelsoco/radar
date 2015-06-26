package com.radar.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.radar.app.R;

/**
 * Created by razelsoco on 26/6/15.
 */
public class Step3Fragment extends BaseFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup3,container,false);

        view.findViewById(R.id.iv_back).setOnClickListener(this);
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
            default:
                break;

        }
    }
}
