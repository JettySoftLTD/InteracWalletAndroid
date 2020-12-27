package com.iq.interac.interacwallet.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iq.interac.interacwallet.coreapp.SuperActivity;
import com.iq.interac.interacwallet.databinding.InteracAmountSentTransactionsBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracServiceCallHelper;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracWalletInfoServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.adapters.InteracAmountSentActivityAdapter;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

import java.util.Arrays;

public class InteracAmountSentActivity extends SuperActivity implements InteracPublicKeyServiceCallHelper, InteracServiceCallHelper {
    InteracAmountSentTransactionsBinding binding;
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracWalletInfoServiceCall interacWalletInfoServiceCall;
    InteracAmountSentActivityAdapter interacAmountSentActivityAdapter;
    int walletId = 0, merchantId = 0, customerId = 0;
    String userName = null, password = null, paymentEmail = null, customerPhoneNumber = null, branchId = null;
    double balanceAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = InteracAmountSentTransactionsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
            interacWalletInfoServiceCall = new InteracWalletInfoServiceCall(this);
            interacAmountSentActivityAdapter = new InteracAmountSentActivityAdapter(this, this);
            binding.amountTransRecylerView.setAdapter(interacAmountSentActivityAdapter);

            walletId = getIntent().getExtras().getInt("walletId");
            merchantId = getIntent().getExtras().getInt("merchantId");
            customerId = getIntent().getExtras().getInt("customerId");
            branchId = getIntent().getExtras().getString("branchId");
            paymentEmail = getIntent().getExtras().getString("paymentEmail");
            userName = getIntent().getExtras().getString("userName");
            password = getIntent().getExtras().getString("password");
            customerPhoneNumber = getIntent().getExtras().getString("customerPhoneNumber");
            balanceAmount = getIntent().getExtras().getDouble("balanceAmount");

            binding.sendMoneySuggestionButton.setOnClickListener(v -> goToWalletTransfer());
            binding.sendMoneyButton.setOnClickListener(v -> goToWalletTransfer());
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
                interacWalletInfoServiceCall.getAmountSent(message, walletId, merchantId, customerId);
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
                interacAmountSentActivityAdapter.setList(Arrays.asList(imb.getWalletTransfers()));
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
                binding.amountSentCard.setVisibility(View.GONE);
                binding.suggestionTxt.setVisibility(View.GONE);
                binding.sendMoneySuggestionButton.setVisibility(View.GONE);

                binding.sendMoneyButton.setVisibility(View.VISIBLE);
                binding.amountTransRecylerView.setVisibility(View.VISIBLE);
            } else {
                binding.sendMoneyButton.setVisibility(View.GONE);
                binding.amountTransRecylerView.setVisibility(View.GONE);

                binding.amountSentCard.setVisibility(View.VISIBLE);
                binding.suggestionTxt.setVisibility(View.VISIBLE);
                binding.sendMoneySuggestionButton.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private Intent putExtras(Intent intent){
        intent.putExtra("walletId", walletId);
        intent.putExtra("customerId", customerId);
        intent.putExtra("branchId", branchId);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);
        intent.putExtra("paymentEmail", paymentEmail);
        intent.putExtra("customerPhoneNumber", customerPhoneNumber);
        intent.putExtra("balanceAmount", balanceAmount);
        return intent;
    }

    private void goToWalletTransfer() {
        try {
            Intent goToWalletTransfer = new Intent(this, InteracWalletTransfersActivity.class);
            startActivity(putExtras(goToWalletTransfer));
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
