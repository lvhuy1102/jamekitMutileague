package com.hcpt.multileagues.configs;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by GL62 on 7/5/2017.
 */

public class AppUtils {
    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }

    }

    public static SpannableString underLine(String text) {
        String mystring = new String(text);
        SpannableString content = new SpannableString(mystring);
        content.setSpan(new UnderlineSpan(), 0, mystring.length(), 0);
        return content;
    }

    public static boolean checkPlayServices(Activity activity) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, 9000)
                        .show();
            } else {
                Log.i("eee", "This device is not supported.");
                Toast.makeText(activity, "This device is not supported google play", Toast.LENGTH_LONG).show();
                activity.finish();
            }
            return false;
        }
        return true;
    }
}
