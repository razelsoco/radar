package com.radar.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.radar.app.R;
import com.radar.app.helper.ConstHelper;
import com.radar.app.helper.PreferenceHelper;

/**
 * Created by razelsoco on 26/6/15.
 */
public class Step1Fragment extends BaseFragment implements View.OnClickListener {
    private RadioGroup rgDeviceType;
    private RadioButton rbCompanyDevice, rbPersonalDevice;

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

        rgDeviceType = (RadioGroup) view.findViewById(R.id.rg_device_type);
        rbCompanyDevice = (RadioButton) view.findViewById(R.id.rb_company);
        rbPersonalDevice = (RadioButton) view.findViewById(R.id.rb_company);

        rgDeviceType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_company){
                    PreferenceHelper.setKeyDeviceType(ConstHelper.DEVICE_TYPE_COMPANY);
                }else{
                    PreferenceHelper.setKeyDeviceType(ConstHelper.DEVICE_TYPE_PERSONAL);
                }
            }
        });


        if(PreferenceHelper.getKeyDeviceType().equals(ConstHelper.DEVICE_TYPE_COMPANY)){
//            rbCompanyDevice.setChecked(true);
//            rbPersonalDevice.setChecked(false);
            rgDeviceType.check(R.id.rb_company);
        }else{
//            rbCompanyDevice.setChecked(false);
//            rbPersonalDevice.setChecked(true);
            rgDeviceType.check(R.id.rb_personal);
        }

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_next:
                //TODO get current selected and proceed to next screen according to selection
                if(PreferenceHelper.getKeyDeviceType().equals(ConstHelper.DEVICE_TYPE_COMPANY)) {
                    replaceFragment(getActivity(), Step2CompanyFragment.class.getName(), new Step2CompanyFragment());
                }else{
                    replaceFragment(getActivity(), Step2PersonalFragment.class.getName(), new Step2PersonalFragment());
                }
                break;
            default:
                break;

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
