package com.iq.interac.interacwallet.functions;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iq.interac.interacwallet.R;

import androidx.appcompat.app.AlertDialog;

public class LoaderFunction {
    private static Activity activity;
    private static AlertDialog alertDialog;
    private final View dialogview;

    public LoaderFunction(Activity activity) {
        Context initialContext = activity.getApplicationContext();
        LayoutInflater inflater = LayoutInflater.from(initialContext);
        final ViewGroup nullParent = null;
        dialogview = inflater.inflate(R.layout.loader_layout, nullParent);
        //ProgressBar progressbar = (ProgressBar) dialogview.findViewById(R.id.progressbar);
        alertDialog = getAlertDialog(activity);
        LoaderFunction.activity = activity;
    }

    public static LoaderFunction getInstance(Activity activity) {
        return new LoaderFunction(activity);
    }

    private AlertDialog getAlertDialog(Activity activity) {
        if (activity != null) {
            try {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                alertDialogBuilder.setView(dialogview);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setDimAmount(0);
                return alertDialog;
            } catch (Exception e) {
                if (ScreenNavKeys.printException) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void showLoader() {
        try {
            activity.runOnUiThread(() -> {
                if (!activity.isFinishing()) {
                    alertDialog.show();
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    public void hideLoader() {
        try {
            activity.runOnUiThread(() -> {
                if (!activity.isFinishing()) {
                    alertDialog.dismiss();
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    public void dismiss() {
        try {
            activity.runOnUiThread(() -> {
                if (!activity.isFinishing()) {
                    alertDialog.dismiss();
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
