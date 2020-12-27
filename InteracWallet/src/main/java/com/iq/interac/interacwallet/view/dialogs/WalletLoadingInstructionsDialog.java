package com.iq.interac.interacwallet.view.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;

import com.iq.interac.interacwallet.databinding.InteracWalletLoadInstructionsBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.util.MethodCallHelper;

public class WalletLoadingInstructionsDialog {
    Activity activity;
    InteracWalletLoadInstructionsBinding binding;
    DisplayMetrics metrics;

    public WalletLoadingInstructionsDialog(Activity activity) {
        this.activity = activity;
    }

    public void showInstructions(String payEmail) {
        try {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
            binding = InteracWalletLoadInstructionsBinding.inflate(activity.getLayoutInflater());
            alertDialogBuilder.setView(binding.getRoot());
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setCancelable(false);
            alertDialog.show();

            binding.yesUnderstoodButton.setOnClickListener(v -> alertDialog.dismiss());

            metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            MethodCallHelper.getInstance(activity).displayDialogsByScreen(metrics, alertDialog, true);

            if (payEmail != null) {
                binding.commingsoonLayout.setVisibility(View.GONE);
                binding.instructionsLayout.setVisibility(View.VISIBLE);

                binding.stepTwotxt.setText("Step 2 : Add following email ( " + payEmail + " ) as Payee");
                binding.stepThreetxt.setText("Step 3 : Start sending money to ( " + payEmail + " ) and provide your Registered PhoneNumber of your account in Interac transfer Message Box");
            } else {
                binding.instructionsLayout.setVisibility(View.GONE);
                binding.commingsoonLayout.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }
}
