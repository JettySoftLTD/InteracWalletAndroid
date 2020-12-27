package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracPaymentsTransactionBean extends BinaryTrade implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id, merchantId, customerId, walletId;
    private String invoiceId, transactionDate, status, transactionReference, date, fullName, phoneNumber, email, branchName;
    private double amountPaid, amountToPay;

    public int getId() {
        return id;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getWalletId() {
        return walletId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public String getDate() {
        return date;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getBranchName() {
        return branchName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getAmountToPay() {
        return amountToPay;
    }
}
