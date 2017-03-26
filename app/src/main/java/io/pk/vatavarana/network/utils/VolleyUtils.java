package io.pk.vatavarana.network.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import io.pk.vatavarana.R;
import io.pk.vatavarana.util.SnackbarUtils;

public class VolleyUtils {

    public static void displayVolleyException(VolleyError volleyError, Context context){

        View view = ((Activity) context).findViewById(android.R.id.content);

        String message;
        if (volleyError instanceof NetworkError) {
            message = context.getString(R.string.volley_networkerror);
        } else if (volleyError instanceof ServerError) {
            message = context.getString(R.string.volley_servererror);
        } else if (volleyError instanceof AuthFailureError) {
            message = context.getString(R.string.volley_authfailureerror);
        } else if (volleyError instanceof ParseError) {
            message = context.getString(R.string.volley_parseerror);
        } else if (volleyError instanceof NoConnectionError) {
            message = context.getString(R.string.volley_connectionerror);
        } else if (volleyError instanceof TimeoutError) {
            message = context.getString(R.string.volley_timeouterror);
        }else{
            message = context.getString(R.string.volley_error);
        }

        SnackbarUtils.make(view, message, Snackbar.LENGTH_LONG);
    }

}
