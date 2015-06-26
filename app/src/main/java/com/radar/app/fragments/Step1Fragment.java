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
public class Step1Fragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup1,container,false);

        view.findViewById(R.id.iv_next).setOnClickListener(this);
        view.findViewById(R.id.iv_back).setVisibility(View.INVISIBLE);
        ((TextView)view.findViewById(R.id.tv_step)).setText(getString(R.string.step1));
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_next:
                //TODO get current selected and proceed to next screen according to selection
                replaceFragment(getActivity(), Step2aFragment.class.getName(),new Step2aFragment());
                break;
            default:
                break;

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name","raz");
    }
}
