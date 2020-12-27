package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracMainBean extends BinaryTrade implements Serializable {
    private static final long serialVersionUID = 1L;
    private int status;
    private String message, publickey, transactionReference;
    private double balance, amountDeducted;

    // Child
    private InteracBranchInfoBean branchInfo;
    private InteracMerchantInfoBean merchantInfo;
    private InteracWalletTransactionsBean[] walletTransactions;
    private InteracPaymentsTransactionBean[] paymentTransactions;
    private InteracWalletTransfersBean[] walletTransfers;
    private InteracWalletBalanceInfoBean walletInfo;
    private InteracDashboardInfoBean dashboardInfo;
    private InteracWalletBalanceInfoBean[] walletAllInfo;

    public InteracBranchInfoBean getBranchInfo() {
        return branchInfo;
    }

    public InteracMerchantInfoBean getMerchantInfo() {
        return merchantInfo;
    }

    public InteracWalletTransactionsBean[] getWalletTransactions() {
        return walletTransactions;
    }

    public InteracPaymentsTransactionBean[] getPaymentTransactions() {
        return paymentTransactions;
    }

    public InteracWalletTransfersBean[] getWalletTransfers() {
        return walletTransfers;
    }

    public InteracWalletBalanceInfoBean getWalletInfo() {
        return walletInfo;
    }

    public InteracDashboardInfoBean getDashboardInfo() {
        return dashboardInfo;
    }

    public InteracWalletBalanceInfoBean[] getWalletAllInfo() {
        return walletAllInfo;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPublickey() {
        return publickey;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmountDeducted() {
        return amountDeducted;
    }
}
