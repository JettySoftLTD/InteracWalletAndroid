package com.iq.interac.interacwallet.view.activities;

import android.os.Bundle;
import android.view.View;

import com.iq.interac.interacwallet.databinding.InteracAmountLoadedTransactionsBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracServiceCallHelper;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracWalletInfoServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.adapters.InteracAmountLoadedActivityAdapter;
import com.iq.interac.interacwallet.view.dialogs.WalletLoadingInstructionsDialog;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class InteracAmountLoadedActivity extends AppCompatActivity implements InteracPublicKeyServiceCallHelper, InteracServiceCallHelper {
    InteracAmountLoadedTransactionsBinding binding;
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracWalletInfoServiceCall interacWalletInfoServiceCall;
    InteracAmountLoadedActivityAdapter interacAmountLoadedActivityAdapter;
    WalletLoadingInstructionsDialog walletLoadingInstructionsDialog;
    int walletId = 0, merchantId = 0, customerId = 0;
    String userName = null, password = null, paymentEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = InteracAmountLoadedTransactionsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
            interacWalletInfoServiceCall = new InteracWalletInfoServiceCall(this);
            walletLoadingInstructionsDialog = new WalletLoadingInstructionsDialog(this);
            interacAmountLoadedActivityAdapter = new InteracAmountLoadedActivityAdapter(this);
            binding.amountTransRecylerView.setAdapter(interacAmountLoadedActivityAdapter);

            binding.addMoneyButton.setOnClickListener(v -> walletLoadingInstructionsDialog.showInstructions(paymentEmail));

            walletId = getIntent().getExtras().getInt("walletId");
            merchantId = getIntent().getExtras().getInt("merchantId");
            customerId = getIntent().getExtras().getInt("customerId");
            paymentEmail = getIntent().getExtras().getString("paymentEmail");
            userName = getIntent().getExtras().getString("userName");
            password = getIntent().getExtras().getString("password");

        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            interacPublicKeyServiceCall.getInteracPublicKey(merchantId, userName, password);
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyInteracPublicKeyResponse(boolean isSuccess, String message, InteracMainBean imb) {
        try {
            if (isSuccess) { //get wallet info
                interacWalletInfoServiceCall.getAmountLoaded(message, walletId, merchantId, customerId);
            } else {
                MethodCallHelper.getInstance(this).showToast(message);
                displyUI(false);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyInteracServiceResponse(boolean isSuccess, String message, InteracMainBean imb) {
        try {
            if (isSuccess) { //set adapter
                displyUI(true);
                interacAmountLoadedActivityAdapter.setList(Arrays.asList(imb.getWalletTransactions()));
            } else {
                MethodCallHelper.getInstance(this).showToast(message);
                displyUI(false);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private void displyUI(boolean isDataPresent){
        try {
           if(isDataPresent){
               binding.amountLoadedCard.setVisibility(View.GONE);
               binding.suggestionTxt.setVisibility(View.GONE);
               binding.addMoneyButton.setVisibility(View.GONE);
               binding.amountTransRecylerView.setVisibility(View.VISIBLE);
           } else {
               binding.amountTransRecylerView.setVisibility(View.GONE);
               binding.amountLoadedCard.setVisibility(View.VISIBLE);
               binding.suggestionTxt.setVisibility(View.VISIBLE);
               binding.addMoneyButton.setVisibility(View.VISIBLE);
           }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
