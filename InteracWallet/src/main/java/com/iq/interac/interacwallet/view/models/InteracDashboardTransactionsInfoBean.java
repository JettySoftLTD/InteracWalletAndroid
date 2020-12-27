package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracDashboardTransactionsInfoBean extends BinaryTrade implements Serializable {

    private static final long serialVersionUID = 1L;
    private int totalSuccessfullPayments, totalPaymentsInQue, totalPartialPayments, totalPaymentsMadeNotWithInterac;
    private double amountSuccessfullPayments, amountPaymentsInQue, amountPartialPayments, amountPaymentsMadeNotWithInterac;

    public int getTotalSuccessfullPayments() {
        return totalSuccessfullPayments;
    }

    public int getTotalPaymentsInQue() {
        return totalPaymentsInQue;
    }

    public int getTotalPartialPayments() {
        return totalPartialPayments;
    }

    public int getTotalPaymentsMadeNotWithInterac() {
        return totalPaymentsMadeNotWithInterac;
    }

    public double getAmountSuccessfullPayments() {
        return amountSuccessfullPayments;
    }

    public double getAmountPaymentsInQue() {
        return amountPaymentsInQue;
    }

    public double getAmountPartialPayments() {
        return amountPartialPayments;
    }

    public double getAmountPaymentsMadeNotWithInterac() {
        return amountPaymentsMadeNotWithInterac;
    }
}
