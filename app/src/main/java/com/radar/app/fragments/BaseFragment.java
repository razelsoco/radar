package com.radar.app.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.radar.app.R;

/**
 * Created by razelsoco on 26/6/15.
 */
public class BaseFragment extends Fragment {
    private ProgressDialogFragment pd;
    protected void showProgressDialog(){
        if(pd == null)
            pd = ProgressDialogFragment.newInstance(getString(R.string.progress_loading));

        pd.show(getFragmentManager(),ProgressDialogFragment.class.getName());
    }

    protected void showProgressDialog(String message){
        if(pd == null)
            pd = ProgressDialogFragment.newInstance(message);
        pd.show(getFragmentManager(),ProgressDialogFragment.class.getName());
    }

    protected void dismissProgressDialog(){
        if(pd != null)
            pd.dismiss();
    }

    public void replaceFragment(FragmentActivity context,
                                    String tag, Fragment f) {
        context.getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.content_frame, f).commit();
    }

    protected void popbackstack(){
        ((FragmentActivity) getActivity()).getSupportFragmentManager().popBackStack();
    }
}
