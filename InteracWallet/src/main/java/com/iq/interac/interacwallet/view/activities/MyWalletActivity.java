package com.iq.interac.interacwallet.view.activities;

import android.content.Intent;
import android.os.Bundle;

import com.iq.interac.interacwallet.R;
import com.iq.interac.interacwallet.coreapp.SuperActivity;
import com.iq.interac.interacwallet.databinding.InteracCustomerDashboardBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.RetrofitService;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracWalletInfoServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.dialogs.WalletLoadingInstructionsDialog;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

public class MyWalletActivity extends SuperActivity implements InteracPublicKeyServiceCallHelper, InteracServiceCallHelper {
    InteracCustomerDashboardBinding binding;
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracWalletInfoServiceCall interacWalletInfoServiceCall;
    WalletLoadingInstructionsDialog walletLoadingInstructionsDialog;
    int walletId = 0, merchantId = 0, customerId = 0;
    String userName = null, password = null, paymentEmail = null, customerPhoneNumber = null, branchId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InteracCustomerDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try {
            RetrofitService.getInstance();

            userName = getIntent().getExtras().getString("userName");
            password = getIntent().getExtras().getString("password");
            paymentEmail = getIntent().getExtras().getString("paymentEmail");
            customerPhoneNumber = getIntent().getExtras().getString("customerPhoneNumber");
            branchId = getIntent().getExtras().getString("branchId");
            merchantId = getIntent().getExtras().getInt("merchantId");
            customerId = getIntent().getExtras().getInt("customerId");

            interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
            interacWalletInfoServiceCall = new InteracWalletInfoServiceCall(this);
            walletLoadingInstructionsDialog = new WalletLoadingInstructionsDialog(this);

            binding.onSwipeRefreshWalletDashboard.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorPrimaryDark),
                    getResources().getColor(R.color.colorPrimaryDark));
            binding.onSwipeRefreshWalletDashboard.setOnRefreshListener(() -> {
                interacPublicKeyServiceCall.getInteracPublicKey(merchantId, userName, password);
                binding.onSwipeRefreshWalletDashboard.setRefreshing(false);
            });

            binding.addMoneyButton.setOnClickListener(v -> walletLoadingInstructionsDialog.showInstructions(paymentEmail));
            binding.amountLoadedCard.setOnClickListener(v -> goToAmountLoaded());
            binding.amountUsedCard.setOnClickListener(v -> goToAmountUsed());
            binding.amountReceivedCard.setOnClickListener(v -> goToAmountReceived());
            binding.amountSentCard.setOnClickListener(v -> goToAmountSent());
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
                interacWalletInfoServiceCall.getWalletInfo(message, merchantId, customerId, branchId);
            } else {
                MethodCallHelper.getInstance(this).showToast(message);
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
            if (isSuccess) { //set wallet info
                walletId = imb.getWalletInfo().getWalletId();
                binding.totalBalance.setText(MethodCallHelper.getInstance(this).getCurrencySymbol() + " " + imb.getWalletInfo().getTotalBalance());
                binding.amountLoadedTotal.setText(MethodCallHelper.getInstance(this).getCurrencySymbol() + " " + imb.getWalletInfo().getTotalAmountLoaded());
                binding.amountUsedTotal.setText(MethodCallHelper.getInstance(this).getCurrencySymbol() + " " + imb.getWalletInfo().getTotalAmountUsed());
                binding.amountReceivedTotal.setText(MethodCallHelper.getInstance(this).getCurrencySymbol() + " " + imb.getWalletInfo().getTotalAmountReceived());
                binding.amountSentTotal.setText(MethodCallHelper.getInstance(this).getCurrencySymbol() + " " + imb.getWalletInfo().getTotalAmountTransferred());
            } else { //try to create wallet and then make the same call to get the wallet info
                MethodCallHelper.getInstance(this).showToast(message);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private Intent putExtras(Intent intent) {
        intent.putExtra("walletId", walletId);
        intent.putExtra("customerId", customerId);
        intent.putExtra("branchId", branchId);
        intent.putExtra("merchantId", merchantId);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);
        intent.putExtra("paymentEmail", paymentEmail);
        intent.putExtra("customerPhoneNumber", customerPhoneNumber);
        intent.putExtra("balanceAmount", Double.parseDouble(binding.totalBalance.getText().toString().replaceAll("\\$", "").replaceAll("\\₹", "")));
        return intent;
    }

    private void goToAmountLoaded() {
        try {
            Intent gotoAmountLoaded = new Intent(this, InteracAmountLoadedActivity.class);
            startActivity(putExtras(gotoAmountLoaded));
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private void goToAmountUsed() {
        try {
            Intent gotoAmountUsed = new Intent(this, InteracAmountUsedActivity.class);
            startActivity(putExtras(gotoAmountUsed));
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private void goToAmountReceived() {
        try {
            Intent gotoAmountReceived = new Intent(this, InteracAmountReceivedActivity.class);
            startActivity(putExtras(gotoAmountReceived));
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private void goToAmountSent() {
        try {
            Intent gotoAmountSent = new Intent(this, InteracAmountSentActivity.class);
            startActivity(putExtras(gotoAmountSent));
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
