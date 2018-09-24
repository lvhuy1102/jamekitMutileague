package com.hcpt.multileagues.volley;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;
import com.firebase.client.Firebase;

import java.util.Objects;

/**
 * Created by pham on 20/10/2015.
 */
public class ControllerRequest extends Application {
    private RequestQueue requestQueue;
    public static final String TAG = ControllerRequest.class.getSimpleName();
    private static ControllerRequest controller;

    // GA
    private static final int GA_DISPATCH_PERIOD = 30;

    // Prevent hits from being sent to reports, i.e. during testing.
    private static final boolean GA_IS_DRY_RUN = false;


    private static final String TRACKING_PREF_KEY = "trackingPreference";

    private static final String GOOGLE_ANALYTIC_KEY = "UA-52413174-6";

    @Override
    public void onCreate() {
        super.onCreate();
        controller = this;
        Firebase.setAndroidContext(this);
        Stetho.initializeWithDefaults(this);
    }

    /**
     * @return
     */

    public static ControllerRequest getInstance() {
        return controller;
    }

    /**
     * @return trả về một đối tượng của RequestQueue sử dụng để gửi request
     */
    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * @param request một request bất kì
     * @param tag     được sử dụng setTag cho request
     * @param <T>     tham số extends từ Object
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }

    /**
     * @param request
     * @param <T>     tham số extends từ Object
     */
    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);

    }

    /**
     * @param tag
     */
    public void cancelRequest(Objects tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    /* Start GA */


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
