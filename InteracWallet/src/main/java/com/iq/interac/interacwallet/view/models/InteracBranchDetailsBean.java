package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracBranchDetailsBean extends BinaryTrade implements Serializable {

    private static final long serialVersionUID = 1L;

    int branchCountryCode;
    String branchId, branchName, branchPaymentEmail, branchCurrency, branchAddress, branchContactFirstName, branchContactLastName, branchStatus, createdDate, updatedDate, branchPhoneNumber;

    public int getBranchCountryCode() {
        return branchCountryCode;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getBranchPaymentEmail() {
        return branchPaymentEmail;
    }

    public String getBranchCurrency() {
        return branchCurrency;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public String getBranchContactFirstName() {
        return branchContactFirstName;
    }

    public String getBranchContactLastName() {
        return branchContactLastName;
    }

    public String getBranchStatus() {
        return branchStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getBranchPhoneNumber() {
        return branchPhoneNumber;
    }
}
