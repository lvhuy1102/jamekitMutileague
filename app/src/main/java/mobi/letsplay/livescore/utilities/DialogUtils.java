package mobi.letsplay.livescore.utilities;

import mobi.letsplay.livescore.R;

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
				mobi.letsplay.livescore.R.drawable.bg_black_transparent);
		setContentView(mobi.letsplay.livescore.R.layout.layout_progress_dialog);
	}
}
