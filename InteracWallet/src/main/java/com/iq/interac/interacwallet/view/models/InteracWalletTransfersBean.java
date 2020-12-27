package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracWalletTransfersBean extends BinaryTrade implements Serializable {
    private static final long serialVersionUID = 1L;
    private String transferReference, sendingPhoneNumber, receivingPhoneNumber, sendingCustName, receivingCustName, transactionDate;
    private int id, sendingCustomerId, recevingCustomerId;
    private double sentAmount;

    public String getTransferReference() {
        return transferReference;
    }

    public String getSendingPhoneNumber() {
        return sendingPhoneNumber;
    }

    public String getReceivingPhoneNumber() {
        return receivingPhoneNumber;
    }

    public String getSendingCustName() {
        return sendingCustName;
    }

    public String getReceivingCustName() {
        return receivingCustName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getId() {
        return id;
    }

    public int getSendingCustomerId() {
        return sendingCustomerId;
    }

    public int getRecevingCustomerId() {
        return recevingCustomerId;
    }

    public double getSentAmount() {
        return sentAmount;
    }
}
