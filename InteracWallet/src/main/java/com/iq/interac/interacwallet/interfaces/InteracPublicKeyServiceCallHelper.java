package com.iq.interac.interacwallet.interfaces;

import com.iq.interac.interacwallet.view.models.InteracMainBean;

public interface InteracPublicKeyServiceCallHelper {
    void notifyInteracPublicKeyResponse(boolean isSuccess, String message, InteracMainBean imb);
}
