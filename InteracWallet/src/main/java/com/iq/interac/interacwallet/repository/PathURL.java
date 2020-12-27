package com.iq.interac.interacwallet.repository;

public interface PathURL {
    String v1 = "_v1";

    String mainUrl = "https://jettysoftcloudapi.eastus.cloudapp.azure.com/";
    //Web db calls
    String BSAPIUrl = mainUrl + "BusinessStore_API/bs/dao/";

    //**********Interac START*************//
    String getInteracPublicKey = BSAPIUrl + "getPublicKeys?";
    String getWalletInfo = BSAPIUrl + "walletInfo?";
    String getWalletTransactions = BSAPIUrl +"walletTransactions?";
    String getPaymentTransactions = BSAPIUrl +"paymentTransactions?";
    String getAmountReceivedTransactions = BSAPIUrl +"receivedTransfersHistory?";
    String getAmountSentTransactions = BSAPIUrl +"sentTransfersHistory?";
    String transferFunds = BSAPIUrl +"transferFunds?";
    String createWallet = BSAPIUrl+"createWallet?";
    String chargePayment = BSAPIUrl+"chargePayment?";
    //**********Interac END*************//
}