package com.iq.interac.interacwallet.coreapp;

import android.app.Activity;
import android.content.Intent;

import com.iq.interac.interacwallet.functions.LoaderFunction;
import com.iq.interac.interacwallet.view.ChargePayment;
import com.iq.interac.interacwallet.view.CreateWallet;
import com.iq.interac.interacwallet.view.activities.MyWalletActivity;


public class InteracWallet {
    public static LoaderFunction loader = null;
    private Activity activity = null;

    //Initializes the class
    public InteracWallet(Activity activity) {
        this.activity = activity;
        loader = LoaderFunction.getInstance(activity);
    }

    /*
     *  --------------------
     *  PARAMETERS
     *  --------------------
     *  @userName = Username created while registering for Interac Wallet Payments
     *  @password = Password given while registering for Interac Wallet Payments
     *  @paymentEmail = Email ID where end customers will be sending money
     *  @customerPhoneNumber = Phone number registered with Account. Important to provide PhoneNumber in Message Box of every payment by Interac
     *  @merchantId = Merchant ID is allocated by Interac Payments ( you can get it from https://iqbizsuite.com after login )
     *  @branchId = Branch ID is given by you when you created branch ( you can get it from https://iqbizsuite.com after login )
     *  @customerId = Customer ID assigned by you, which is also used while creating customer + wallet
     * */
    public void openWalletInfo(String userName, String password, String paymentEmail, String customerPhoneNumber, int merchantId, String branchId, int customerId) throws Exception {
        Intent intent = new Intent(activity, MyWalletActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("password", password);
        intent.putExtra("paymentEmail", paymentEmail);
        intent.putExtra("customerPhoneNumber", customerPhoneNumber);
        intent.putExtra("merchantId", merchantId);
        intent.putExtra("branchId", branchId);
        intent.putExtra("customerId", customerId);
        activity.startActivity(intent);
    }

    /* Create or Update Wallet Info
     *  Note: to get the response of the service, implement class called ( InteracCreateWalletServiceCallHelper ) and methods
     *
     *  --------------------
     *  PARAMETERS
     *  --------------------
     *  @userName = Username created while registering for Interac Wallet Payments
     *  @password = Password given while registering for Interac Wallet Payments
     *  @customerPhoneNumber = Phone number registered with Account. Important to provide PhoneNumber in Message Box of every payment by Interac
     *  @firstName = Customer First Name
     *  @lastName = Customer Last Name
     *  @customerEmail = Email of the Customer
     *  @merchantId = Merchant ID is allocated by Interac Payments ( you can get it from https://iqbizsuite.com after login )
     *  @customerId = Customer ID assigned by you, which is also used while creating customer + wallet
     *  @countryCode = Country code of the customer phone number provided
     * */
    public void createWallet(String userName, String password, String customerPhoneNumber, String firstName, String lastName, String customerEmail, int merchantId,
                             int customerId, int countryCode) throws Exception {
        new CreateWallet(activity, userName, password, customerPhoneNumber, firstName, lastName, customerEmail, merchantId, customerId, countryCode);
    }

    /* Charge Payment
     *  Note: to get the response of the service, implement class called ( InteracChargePaymentServiceCallHelper ) and methods
     *
     *  --------------------
     *  PARAMETERS
     *  --------------------
     *  @userName = Username created while registering for Interac Wallet Payments
     *  @password = Password given while registering for Interac Wallet Payments
     *  @merchantId = Merchant ID is allocated by Interac Payments ( you can get it from https://iqbizsuite.com after login )
     *  @customerId = Customer ID assigned by you, which is also used while creating customer + wallet
     *  @branchId = Branch ID is given by you when you created branch ( you can get it from https://iqbizsuite.com after login )
     *  @invoiceId = Invoice ID of the charge needs to make
     *  @amountInCents = Amount to be charged from Customer Wallet
     * */
    public void chargePayment(String userName, String password, int merchantId, String branchId, int customerId, String invoiceId, Long amountInCents) throws Exception {
        new ChargePayment(activity, userName, password, merchantId, branchId, customerId, invoiceId, amountInCents);
    }
}
