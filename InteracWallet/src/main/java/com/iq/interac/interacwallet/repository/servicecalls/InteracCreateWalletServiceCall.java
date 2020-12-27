package com.iq.interac.interacwallet.repository.servicecalls;

import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracAPI;
import com.iq.interac.interacwallet.interfaces.InteracCreateWalletServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.RetrofitService;
import com.iq.interac.interacwallet.repository.PathURL;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

public class InteracCreateWalletServiceCall {

    InteracCreateWalletServiceCallHelper listener;

    public InteracCreateWalletServiceCall(InteracCreateWalletServiceCallHelper listener) {
        this.listener = listener;
    }

    public void createInteracWallet(String token, int merchantId, int customerId, String firstName, String lastName, int countryCode, String phoneNumber, String eMail) {
        try {
            String url =
                    PathURL.createWallet + "token=" + token + "&merchantId=" + merchantId + "&customerId=" + customerId + "&firstName=" + firstName + "&lastName=" + lastName + "&countryCode=" + countryCode +
                            "&phoneNumber=" + phoneNumber + " &email=" + eMail;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        listener.notifyInteracCreateWalletResponse(true, message);
                    } else {
                        listener.notifyInteracCreateWalletResponse(false, message);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracCreateWalletResponse(false, errormessage);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
