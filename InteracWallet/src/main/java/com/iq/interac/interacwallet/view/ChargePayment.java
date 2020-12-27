package com.iq.interac.interacwallet.view;

import android.app.Activity;

import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracChargePaymentServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracCreateWalletServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.repository.servicecalls.InteracChargePaymentServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

public class ChargePayment implements InteracPublicKeyServiceCallHelper, InteracChargePaymentServiceCallHelper {
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracChargePaymentServiceCall interacChargePaymentServiceCall;

    Activity activity;
    int merchantId = 0, customerId = 0;
    String branchId = null, invoiceId = null;
    double amount;

    public ChargePayment(Activity activity, String userName, String password, int merchantId, String branchId, int customerId, String invoiceId, Long amountInCents){
        interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
        interacChargePaymentServiceCall = new InteracChargePaymentServiceCall(this);

        this.activity = activity;
        this.merchantId = merchantId;
        this.branchId = branchId;
        this.customerId = customerId;
        this.invoiceId = invoiceId;
        this.amount = MethodCallHelper.getInstance(activity).centsToDollar(amountInCents);

        interacPublicKeyServiceCall.getInteracPublicKey(merchantId, userName, password);
    }

    @Override
    public void notifyInteracPublicKeyResponse(boolean isSuccess, String message, InteracMainBean imb) {
        try {
            if (isSuccess) { //create wallet
                interacChargePaymentServiceCall.chargePayment(message, merchantId, branchId, customerId, invoiceId, amount);
            } else {
                MethodCallHelper.getInstance(activity).showToast(message);
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyInteracChargePaymentResponse(boolean isSuccess, String message) {
        MethodCallHelper.getInstance(activity).showToast(message);
    }
}
