package com.iq.interac.interacwallet.view.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.iq.interac.interacwallet.coreapp.SuperActivity;
import com.iq.interac.interacwallet.databinding.InteracWalletTransferBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.CustomErrorHandler;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracServiceCallHelper;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracWalletInfoServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

import java.text.NumberFormat;

public class InteracWalletTransfersActivity extends SuperActivity implements InteracPublicKeyServiceCallHelper, InteracServiceCallHelper {
    InteracWalletTransferBinding binding;
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracWalletInfoServiceCall interacWalletInfoServiceCall;

    double amount = 0, parsed = 0;
    int walletId = 0, merchantId = 0, customerId = 0;
    String userName = null, password = null, paymentEmail = null, branchId = null;
    OnTextChange onTextChange;
    String cleanString = "", formatted = "", currentAmount = "", customerPhoneNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = InteracWalletTransferBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
            interacWalletInfoServiceCall = new InteracWalletInfoServiceCall(this);

            onTextChange = new OnTextChange();
            binding.transferAmountTxt.requestFocus();
            binding.transferAmountTxt.addTextChangedListener(onTextChange);

            amount = getIntent().getExtras().getDouble("balanceAmount");
            walletId = getIntent().getExtras().getInt("walletId");
            merchantId = getIntent().getExtras().getInt("merchantId");
            customerId = getIntent().getExtras().getInt("customerId");
            branchId = getIntent().getExtras().getString("branchId");
            paymentEmail = getIntent().getExtras().getString("paymentEmail");
            userName = getIntent().getExtras().getString("userName");
            password = getIntent().getExtras().getString("password");
            customerPhoneNumber = getIntent().getExtras().getString("customerPhoneNumber");

            if (amount > 0) {
                binding.transferNowButton.setEnabled(true);
                binding.totalBalance.setText(MethodCallHelper.getInstance(this).getCurrencySymbol() + " " + amount);
            } else {
                binding.transferNowButton.setEnabled(false);
            }
            binding.phoneNumber.requestFocus();
            binding.transferNowButton.setOnClickListener(v -> sendMoney());
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
                interacWalletInfoServiceCall.transferFunds(message, walletId, binding.phoneNumber.getText().toString(), Double.parseDouble(binding.transferAmountTxt.getText().toString()),
                        merchantId, customerId, customerPhoneNumber, branchId);
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
            if (isSuccess) {
                deductAmountInTotalBalance();
                MethodCallHelper.getInstance(this).showToast(message);
            } else {
                MethodCallHelper.getInstance(this).showToast(message);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private void deductAmountInTotalBalance() {
        try {
            double currentAmount = Double.parseDouble(binding.totalBalance.getText().toString().replaceAll(" +", "").replaceAll("\\$", "").replaceAll("\\₹", ""));
            double newAmount = currentAmount - Double.parseDouble(binding.transferAmountTxt.getText().toString());
            binding.totalBalance.setText(MethodCallHelper.getInstance(this).getCurrencySymbol() + " " + newAmount);

            binding.phoneNumber.getText().clear();
            binding.transferAmountTxt.getText().clear();
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    private void sendMoney() {
        try {
            if (binding.phoneNumber.getText() == null || binding.phoneNumber.getText().toString().isEmpty()) {
                binding.phoneNumber.setError(CustomErrorHandler.phoneNumber);
                binding.phoneNumber.requestFocus();
            } else if (binding.transferAmountTxt.getText() == null || binding.transferAmountTxt.getText().toString().isEmpty()) {
                binding.transferAmountTxt.setError(CustomErrorHandler.ip_transferamount);
                binding.transferAmountTxt.requestFocus();
            } else if (Double.parseDouble(binding.transferAmountTxt.getText().toString()) > amount) {
                binding.transferAmountTxt.setError(CustomErrorHandler.ip_transferamount_lessthan);
                binding.transferAmountTxt.requestFocus();
            } else {
                interacPublicKeyServiceCall.getInteracPublicKey(merchantId, userName, password);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    class OnTextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                if (s != null) {
                    cleanString = s.toString().replaceAll("[$,.]", "").replaceAll(" +", "").replaceAll("\\$", "").replaceAll("₹", "");
                    parsed = Double.parseDouble(cleanString);
                    formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));
                    currentAmount = formatted.replaceAll(" +", "").replaceAll("\\$", "").replaceAll("₹", "").replaceAll(" +", "").replaceAll("\\,", "");

                    if (binding.transferAmountTxt.getText().hashCode() == s.hashCode()) {
                        binding.transferAmountTxt.removeTextChangedListener(onTextChange);
                        binding.transferAmountTxt.setText(currentAmount);
                        binding.transferAmountTxt.setSelection(currentAmount.length());
                        binding.transferAmountTxt.addTextChangedListener(onTextChange);
                    }
                }
            } catch (Exception e) {
                if (ScreenNavKeys.printException) {
                    e.printStackTrace();
                }
            }
        }
    }
}
