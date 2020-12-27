package com.iq.interac.interacwallet.view.models;

import java.io.Serializable;

public class InteracBranchInfoBean extends BinaryTrade implements Serializable {

    private static final long serialVersionUID = 1L;

    int totalBranches, totalWallets;
    double totalRevenue;
    // child
    InteracBranchDetailsBean[] branchDetails;

    public int getTotalBranches() {
        return totalBranches;
    }

    public int getTotalWallets() {
        return totalWallets;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public InteracBranchDetailsBean[] getBranchDetails() {
        return branchDetails;
    }
}
