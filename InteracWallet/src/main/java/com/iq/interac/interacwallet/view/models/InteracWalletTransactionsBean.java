package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracWalletTransactionsBean extends BinaryTrade implements Serializable {

    private static final long serialVersionUID = 1L;
    private String branchid, referenceNumber, phoneNumber, paymentEmail, status, transactionDate, branchName;
    private double amountDeposited;
    private int walletId, customerId, id;

    public String getBranchid() {
        return branchid;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPaymentEmail() {
        return paymentEmail;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getBranchName() {
        return branchName;
    }

    public double getAmountDeposited() {
        return amountDeposited;
    }

    public int getWalletId() {
        return walletId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getId() {
        return id;
    }
}
