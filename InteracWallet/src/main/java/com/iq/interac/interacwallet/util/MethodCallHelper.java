package com.iq.interac.interacwallet.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.iq.interac.interacwallet.R;
import com.iq.interac.interacwallet.coreapp.InteracWallet;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;

public class MethodCallHelper {
    private static MethodCallHelper instance = null;
    private Activity activity = null;

    public MethodCallHelper(Activity activity) {
        this.activity = activity;
    }

    public static synchronized MethodCallHelper getInstance(Activity activity) {
        if (instance == null) {
            instance = new MethodCallHelper(activity);
        }
        return instance;
    }

    public void displayDialogsByScreen(DisplayMetrics metrics, AlertDialog alertDialog, boolean setRadius) {
        try {
            Log.d("metrics", "" + metrics.densityDpi);
            if (setRadius) {
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.corner_radius);
            }
            switch (metrics.densityDpi) {
                default:
                    alertDialog.getWindow().setLayout(getPxWidthDynamically(), WindowManager.LayoutParams.WRAP_CONTENT);
                    break;
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    private int getPxWidthDynamically() {
        int px = 0;
        try {
            if (isLandScapeMode(activity)) {
                px = (getScreenWidth() - (getScreenWidth() / 3));
            } else {
                px = (getScreenWidth() - (getScreenWidth() / 10));
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
        return px;
    }

    public int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public boolean isLandScapeMode(Activity activity) {
        boolean isLandScape = false;
        try {
            int orientation = activity.getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {// In landscape
                isLandScape = true;
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                // FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
        return isLandScape;
    }

    public void showToast(String message) {
        try {
            Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if (v != null) v.setGravity(Gravity.CENTER);
            toast.show();
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    public String getCurrencySymbol() {
        try {
            String country = "canada"; //supported US & Canada only
            if (country != null && country.toLowerCase().equalsIgnoreCase("india")) {
                return "₹";
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
        return "$";
    }

    public double centsToDollar(long amount) {
        String amountStr = ((Long) amount).toString();
        if (amountStr.length() > 1) {
            String firstStr = amountStr.substring(0, amountStr.length() - 2);
            String secondStr = amountStr.substring(amountStr.length() - 2, amountStr.length());
            String finalStr = firstStr + "." + secondStr;
            // double doubleValue = Double.parseDouble(finalStr);
            return Double.parseDouble(finalStr);
        } else if (amountStr.length() == 1) {
            return (amount / 100d);
        } else {
            return 0;
        }
    }
}
