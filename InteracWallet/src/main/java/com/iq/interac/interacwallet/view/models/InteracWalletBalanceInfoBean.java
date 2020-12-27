package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracWalletBalanceInfoBean extends BinaryTrade implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private double totalAmountLoaded, totalAmountUsed, totalAmountTransferred, totalAmountReceived, totalBalance;
    private int walletId, merchantId, countryCode, customerId;
    private String branchid, firstName, lastName, phoneNumber, email, createdDate, updatedDate, fullName, branchName;

    public double getTotalAmountLoaded() {
        return totalAmountLoaded;
    }

    public double getTotalAmountUsed() {
        return totalAmountUsed;
    }

    public double getTotalAmountTransferred() {
        return totalAmountTransferred;
    }

    public double getTotalAmountReceived() {
        return totalAmountReceived;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public int getWalletId() {
        return walletId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getBranchid() {
        return branchid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBranchName() {
        return branchName;
    }
}
