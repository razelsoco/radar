package com.radar.app.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ProgressDialogFragment extends DialogFragment {
	public static ProgressDialogFragment newInstance(String msg) {
		ProgressDialogFragment frag = new ProgressDialogFragment();
		Bundle b = new Bundle();
		b.putString("msg", msg);
		frag.setArguments(b);
		return frag;
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setCancelable(false);
	}



	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final ProgressDialog dialog = new ProgressDialog(getActivity());
		dialog.setMessage(getArguments().getString("msg"));
//		OnKeyListener keyListener = new OnKeyListener() {
//
//			@Override
//			public boolean onKey(DialogInterface dialog, int keyCode,
//					KeyEvent event) {
//
//				if (keyCode == KeyEvent.KEYCODE_BACK) {
//					return true;
//				}
//				return false;
//			}
//
//		};
//		dialog.setOnKeyListener(keyListener);
		return dialog;
	}
}
