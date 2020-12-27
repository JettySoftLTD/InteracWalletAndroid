package com.iq.interac.interacwallet.view.activities;

import android.os.Bundle;
import android.view.View;
import com.iq.interac.interacwallet.coreapp.SuperActivity;
import com.iq.interac.interacwallet.databinding.InteracAmountReceivedTransactionsBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracServiceCallHelper;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracWalletInfoServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.adapters.InteracAmountReceivedActivityAdapter;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

import java.util.Arrays;

public class InteracAmountReceivedActivity extends SuperActivity implements InteracPublicKeyServiceCallHelper, InteracServiceCallHelper {
    InteracAmountReceivedTransactionsBinding binding;
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracWalletInfoServiceCall interacWalletInfoServiceCall;
    InteracAmountReceivedActivityAdapter interacAmountReceivedActivityAdapter;
    int walletId = 0, merchantId = 0, customerId = 0;
    String userName = null, password = null, paymentEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            binding = InteracAmountReceivedTransactionsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
            interacWalletInfoServiceCall = new InteracWalletInfoServiceCall(this);
            interacAmountReceivedActivityAdapter = new InteracAmountReceivedActivityAdapter(this, this);
            binding.amountTransRecylerView.setAdapter(interacAmountReceivedActivityAdapter);

            walletId = getIntent().getExtras().getInt("walletId");
            merchantId = getIntent().getExtras().getInt("merchantId");
            customerId = getIntent().getExtras().getInt("customerId");
            paymentEmail = getIntent().getExtras().getString("paymentEmail");
            userName = getIntent().getExtras().getString("userName");
            password = getIntent().getExtras().getString("password");
        }catch (Exception e){
            if(ScreenNavKeys.printException){e.printStackTrace();}
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
                interacWalletInfoServiceCall.getAmountReceived(message, walletId, merchantId, customerId);
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
                interacAmountReceivedActivityAdapter.setList(Arrays.asList(imb.getWalletTransfers()));
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
                binding.amountReceivedCard.setVisibility(View.GONE);
                binding.suggestionTxt.setVisibility(View.GONE);
                binding.amountTransRecylerView.setVisibility(View.VISIBLE);
            } else {
                binding.amountTransRecylerView.setVisibility(View.GONE);
                binding.amountReceivedCard.setVisibility(View.VISIBLE);
                binding.suggestionTxt.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
