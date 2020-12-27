package com.iq.interac.interacwallet.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iq.interac.interacwallet.coreapp.RecyclerViewBaseAdapter;
import com.iq.interac.interacwallet.databinding.InteracAmountUsedTransactionsAdapterviewBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.models.InteracPaymentsTransactionBean;

import androidx.annotation.NonNull;

public class InteracAmountUsedActivityAdapter extends RecyclerViewBaseAdapter<InteracPaymentsTransactionBean> {
    Activity activity;

    public InteracAmountUsedActivityAdapter(Context ct, Activity activity) {
        super(ct);
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerViewBaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InteracAmountUsedTransactionsAdapterviewBinding binding = InteracAmountUsedTransactionsAdapterviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AmountUsedTransactionsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBaseAdapter.MyViewHolder holder, int position) {
        try {
            if (holder instanceof AmountUsedTransactionsHolder) {
                AmountUsedTransactionsHolder autholder = (AmountUsedTransactionsHolder) holder;
                InteracPaymentsTransactionBean iptb = getItem(position);

                if (iptb != null) {
                    autholder.binding.invoiceidTxt.setText(iptb.getInvoiceId());
                    autholder.binding.dateTxt.setText(iptb.getTransactionDate());
                    autholder.binding.statusTxt.setText(iptb.getStatus());
                    autholder.binding.amountPaidTxt.setText(MethodCallHelper.getInstance(activity).getCurrencySymbol() + " " + iptb.getAmountPaid());
                }
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    static class AmountUsedTransactionsHolder extends RecyclerViewBaseAdapter.MyViewHolder {
        InteracAmountUsedTransactionsAdapterviewBinding binding;

        private AmountUsedTransactionsHolder(InteracAmountUsedTransactionsAdapterviewBinding binding) {
            super(binding);
            this.binding = binding;
        }
    }
}