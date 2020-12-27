package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracMerchantInfoBean extends BinaryTrade implements Serializable {

    private static final long serialVersionUID = 1L;
    private int merchantId, restrictWallet, devCountryCode, isQueAllowed, isProdAllowed;
    private String devCompanyName, devFirstName, devLastName, devEmail, devMerchantId, merchCompanyName, merchFirstName, merchLastName, merchLogoId, devPhone, webhook;

    public int getMerchantId() {
        return merchantId;
    }

    public int getRestrictWallet() {
        return restrictWallet;
    }

    public int getDevCountryCode() {
        return devCountryCode;
    }

    public int getIsQueAllowed() {
        return isQueAllowed;
    }

    public int getIsProdAllowed() {
        return isProdAllowed;
    }

    public String getDevCompanyName() {
        return devCompanyName;
    }

    public String getDevFirstName() {
        return devFirstName;
    }

    public String getDevLastName() {
        return devLastName;
    }

    public String getDevEmail() {
        return devEmail;
    }

    public String getDevMerchantId() {
        return devMerchantId;
    }

    public String getMerchCompanyName() {
        return merchCompanyName;
    }

    public String getMerchFirstName() {
        return merchFirstName;
    }

    public String getMerchLastName() {
        return merchLastName;
    }

    public String getMerchLogoId() {
        return merchLogoId;
    }

    public String getDevPhone() {
        return devPhone;
    }

    public String getWebhook() {
        return webhook;
    }
}
