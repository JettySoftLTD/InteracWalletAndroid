package com.iq.interac.interacwallet.repository.servicecalls;

import android.util.Base64;

import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.interfaces.InteracAPI;
import com.iq.interac.interacwallet.interfaces.InteracPublicKeyServiceCallHelper;
import com.iq.interac.interacwallet.interfaces.RetrofitService;
import com.iq.interac.interacwallet.repository.PathURL;
import com.iq.interac.interacwallet.security.RSAEncrypt;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

import java.net.URLEncoder;

public class InteracPublicKeyServiceCall {
    InteracPublicKeyServiceCallHelper listener;

    public InteracPublicKeyServiceCall(InteracPublicKeyServiceCallHelper listener) {
        this.listener = listener;
    }

    public void getInteracPublicKey(int merchantId, String userName, String token) {
        try {
            String url = PathURL.getInteracPublicKey + "merchantId=" + merchantId + "&username=" + userName + "&password=" + token;

            RetrofitService.getInstance().postDataInteracObject(url, new InteracAPI() {
                @Override
                public void notifySuccess(int status, String message, InteracMainBean imb) {
                    if (status == 200) {
                        String tokenToEncrypt = merchantId + "-" + userName + "-" + token;
                        try {
                            String token = Base64.encodeToString(RSAEncrypt.encrypt(tokenToEncrypt, imb.getPublickey()), Base64.DEFAULT | Base64.NO_WRAP);
                            listener.notifyInteracPublicKeyResponse(true, URLEncoder.encode(token, "UTF-8"), imb);
                        } catch (Exception e) {
                            listener.notifyInteracPublicKeyResponse(false, "Failed to Generate Token", null);
                            if (ScreenNavKeys.printException) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        listener.notifyInteracPublicKeyResponse(false, message, null);
                    }
                }

                @Override
                public void notifyError(String errormessage) {
                    listener.notifyInteracPublicKeyResponse(false, errormessage, null);
                }
            });
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
            }
        }
    }
}
