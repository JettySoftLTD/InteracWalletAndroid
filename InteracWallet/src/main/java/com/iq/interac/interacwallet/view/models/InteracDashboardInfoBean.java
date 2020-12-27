package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;
import java.util.ArrayList;

public class InteracDashboardInfoBean extends BinaryTrade implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int totalWallets;
    private double totalRevenue, totalSales, totalUpComingSales;
    private InteracDashboardTransactionsInfoBean paymentTransactions;
    private ArrayList<String> totalRevenueList = new ArrayList<String>();
    private ArrayList<String> totalSalesList = new ArrayList<String>();
    private ArrayList<String> totalUpcomingSalesList = new ArrayList<String>();
    private ArrayList<String> amountSuccessfullPaymentsList = new ArrayList<String>();
    private ArrayList<String> amountPaymentsInQueList = new ArrayList<String>();
    private ArrayList<String> amountPartialPaymentsList = new ArrayList<String>();
    private ArrayList<String> amountPaymentsMadeNotWithInteracList = new ArrayList<String>();

    public int getTotalWallets() {
        return totalWallets;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getTotalUpComingSales() {
        return totalUpComingSales;
    }

    public InteracDashboardTransactionsInfoBean getPaymentTransactions() {
        return paymentTransactions;
    }

    public ArrayList<String> getTotalRevenueList() {
        return totalRevenueList;
    }

    public ArrayList<String> getTotalSalesList() {
        return totalSalesList;
    }

    public ArrayList<String> getTotalUpcomingSalesList() {
        return totalUpcomingSalesList;
    }

    public ArrayList<String> getAmountSuccessfullPaymentsList() {
        return amountSuccessfullPaymentsList;
    }

    public ArrayList<String> getAmountPaymentsInQueList() {
        return amountPaymentsInQueList;
    }

    public ArrayList<String> getAmountPartialPaymentsList() {
        return amountPartialPaymentsList;
    }

    public ArrayList<String> getAmountPaymentsMadeNotWithInteracList() {
        return amountPaymentsMadeNotWithInteracList;
    }
}
