package com.iq.interac.interacwallet.view.activities;

import android.os.Bundle;
import android.view.View;

import com.iq.interac.interacwallet.coreapp.SuperActivity;
import com.iq.interac.interacwallet.databinding.InteracAmountUsedTransactionsBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracServiceCallHelper;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracWalletInfoServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.adapters.InteracAmountUsedActivityAdapter;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

import java.util.Arrays;

public class InteracAmountUsedActivity extends SuperActivity implements InteracPublicKeyServiceCallHelper, InteracServiceCallHelper {
    InteracAmountUsedTransactionsBinding binding;
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracWalletInfoServiceCall interacWalletInfoServiceCall;
    InteracAmountUsedActivityAdapter interacAmountUsedActivityAdapter;
    int walletId = 0, merchantId = 0, customerId = 0;
    String userName = null, password = null, paymentEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = InteracAmountUsedTransactionsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
            interacWalletInfoServiceCall = new InteracWalletInfoServiceCall(this);
            interacAmountUsedActivityAdapter = new InteracAmountUsedActivityAdapter(this, this);
            binding.amountTransRecylerView.setAdapter(interacAmountUsedActivityAdapter);

            walletId = getIntent().getExtras().getInt("walletId");
            merchantId = getIntent().getExtras().getInt("merchantId");
            customerId = getIntent().getExtras().getInt("customerId");
            paymentEmail = getIntent().getExtras().getString("paymentEmail");
            userName = getIntent().getExtras().getString("userName");
            password = getIntent().getExtras().getString("password");

            /*binding.menuButton.setText(MethodCallHelper.getInstance(this).getTitleforBusinessType());
            binding.menuSuggestionButton.setText(MethodCallHelper.getInstance(this).getTitleforBusinessType());

            binding.menuButton.setOnClickListener(v -> ScreenNavigation.getInstance().gotoMainMenu_Activity(this));
            binding.menuSuggestionButton.setOnClickListener(v -> ScreenNavigation.getInstance().gotoMainMenu_Activity(this));*/
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
                interacWalletInfoServiceCall.getAmountUsed(message, walletId, merchantId, customerId);
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
                interacAmountUsedActivityAdapter.setList(Arrays.asList(imb.getPaymentTransactions()));
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

    private void displyUI(boolean isDataPresent) {
        try {
            if (isDataPresent) {
                binding.amountUsedCard.setVisibility(View.GONE);
                binding.suggestionTxt.setVisibility(View.GONE);
                binding.menuSuggestionButton.setVisibility(View.GONE);
                binding.menuButton.setVisibility(View.VISIBLE);
                binding.amountTransRecylerView.setVisibility(View.VISIBLE);
            } else {
                binding.amountTransRecylerView.setVisibility(View.GONE);
                binding.menuButton.setVisibility(View.GONE);
                binding.amountUsedCard.setVisibility(View.VISIBLE);
                binding.suggestionTxt.setVisibility(View.VISIBLE);
                binding.menuSuggestionButton.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
