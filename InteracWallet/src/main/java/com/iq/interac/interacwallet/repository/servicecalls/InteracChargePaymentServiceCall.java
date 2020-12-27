package com.iq.interac.interacwallet.repository.servicecalls;

import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracAPI;
import com.iq.interac.interacwallet.interfaces.InteracChargePaymentServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.RetrofitService;
import com.iq.interac.interacwallet.repository.PathURL;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

public class InteracChargePaymentServiceCall {

    InteracChargePaymentServiceCallHelper listener;

    public InteracChargePaymentServiceCall(InteracChargePaymentServiceCallHelper listener) {
        this.listener = listener;
    }

    public void chargePayment(String token, int merchantId, String branchId, int customerId, String invoiceId, double amount) {
        try {
            String url =
                    PathURL.chargePayment + "token=" + token + "&merchantId=" + merchantId + "&customerId=" + customerId + "&branchId=" + branchId + "&invoiceId=" + invoiceId + "&amount=" + amount;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracChargePaymentResponse(true, message);
                    } else {
                        listener.notifyInteracChargePaymentResponse(false, message);
                    }
                }

                @Override
                public void notifyError(String errorMessage) {
                    listener.notifyInteracChargePaymentResponse(false, errorMessage);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
