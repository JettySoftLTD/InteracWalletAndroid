package com.iq.interac.interacwallet.view;

import android.app.Activity;

import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracCreateWalletServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.repository.servicecalls.InteracCreateWalletServiceCall;
import com.iq.interac.interacwallet.repository.servicecalls.InteracPublicKeyServiceCall;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

public class CreateWallet implements InteracPublicKeyServiceCallHelper, InteracCreateWalletServiceCallHelper {
    Activity activity;
    InteracPublicKeyServiceCall interacPublicKeyServiceCall;
    InteracCreateWalletServiceCall interacCreateWalletServiceCall;

    int merchantId = 0, customerId = 0, countryCode = 1;
    String password = null, customerPhoneNumber = null, firstName = null, lastName = null, customerEmail = null;

    public CreateWallet(Activity activity, String userName, String password, String customerPhoneNumber, String firstName, String lastName, String customerEmail, int merchantId,
                        int customerId, int countryCode) throws Exception {
        interacPublicKeyServiceCall = new InteracPublicKeyServiceCall(this);
        interacCreateWalletServiceCall = new InteracCreateWalletServiceCall(this);

        this.activity = activity;
        this.password = password;
        this.customerPhoneNumber = customerPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerEmail = customerEmail;
        this.merchantId = merchantId;
        this.customerId = customerId;
        this.countryCode = countryCode;

        interacPublicKeyServiceCall.getInteracPublicKey(merchantId, userName, password);
    }

    @Override
    public void notifyInteracPublicKeyResponse(boolean isSuccess, String message, InteracMainBean imb) {
        try {
            if (isSuccess) { //create wallet
                interacCreateWalletServiceCall.createInteracWallet(message, merchantId, customerId, firstName, lastName, countryCode, customerPhoneNumber, customerEmail);
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
    public void notifyInteracCreateWalletResponse(boolean isSuccess, String message) {
        MethodCallHelper.getInstance(activity).showToast(message);
    }


}
