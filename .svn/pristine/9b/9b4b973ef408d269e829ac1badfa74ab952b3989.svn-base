package com.hcpt.multileagues.utilities;

import com.hcpt.multileagues.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

public class DialogUtils extends Dialog {

	public DialogUtils(Context context) {
		super(context);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawableResource(
				R.drawable.bg_black_transparent);
		setContentView(R.layout.layout_progress_dialog);
	}
}
