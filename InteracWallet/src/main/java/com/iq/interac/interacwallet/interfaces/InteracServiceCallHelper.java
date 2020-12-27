package com.iq.interac.interacwallet.interfaces;

import com.iq.interac.interacwallet.view.models.InteracMainBean;

public interface InteracServiceCallHelper {
    void notifyInteracServiceResponse(boolean isSuccess, String message, InteracMainBean imb);
}
