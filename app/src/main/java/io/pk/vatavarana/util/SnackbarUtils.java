package io.pk.vatavarana.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import io.pk.vatavarana.R;

public class SnackbarUtils {

    public static void make(View view, String message, int lengthToDisplay) {
        Snackbar snackbar = Snackbar.make(view, message, lengthToDisplay);
        View snackbarView = snackbar.getView();
        Context context = view.getContext();
        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorOrange));
        snackbar.show();
    }
}
