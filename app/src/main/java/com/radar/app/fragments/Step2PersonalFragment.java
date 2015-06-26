package com.radar.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.radar.app.R;
import com.radar.app.helper.PreferenceHelper;

/**
 * Created by razelsoco on 26/6/15.
 */
public class Step2PersonalFragment extends BaseFragment implements View.OnClickListener {
    EditText etName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup2a,container,false);

        view.findViewById(R.id.iv_back).setOnClickListener(this);
        view.findViewById(R.id.iv_next).setOnClickListener(this);
        ((TextView)view.findViewById(R.id.tv_step)).setText(getString(R.string.step2));
        etName = (EditText) view.findViewById(R.id.et_name);
        etName.setText(PreferenceHelper.getUsername());
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_next:
                //TODO get current selected and proceed to next screen according to selection
                replaceFragment(getActivity(), Step3Fragment.class.getName(),new Step3Fragment());
                PreferenceHelper.setUsername(etName.getText().toString());
                break;
            case R.id.iv_back:
                popbackstack();
                break;
            default:
                break;

        }
    }
}
