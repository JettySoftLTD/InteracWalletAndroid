package com.iq.interac.interacwallet.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iq.interac.interacwallet.coreapp.RecyclerViewBaseAdapter;
import com.iq.interac.interacwallet.databinding.InteracAmountLoadedTransactionsAdapterviewBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.view.models.InteracWalletTransactionsBean;

import androidx.annotation.NonNull;

public class InteracAmountLoadedActivityAdapter extends RecyclerViewBaseAdapter<InteracWalletTransactionsBean> {

    public InteracAmountLoadedActivityAdapter(Context ct) {
        super(ct);
    }

    @NonNull
    @Override
    public RecyclerViewBaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InteracAmountLoadedTransactionsAdapterviewBinding binding = InteracAmountLoadedTransactionsAdapterviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AmountLoadedTransactionsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBaseAdapter.MyViewHolder holder, int position) {
        try {
            if (holder instanceof AmountLoadedTransactionsHolder) {
                AmountLoadedTransactionsHolder altholder = (AmountLoadedTransactionsHolder) holder;
                InteracWalletTransactionsBean iwtb = getItem(position);

                if (iwtb != null) {
                    altholder.binding.paidToEmailTxt.setText(iwtb.getPaymentEmail());
                    altholder.binding.referencNumberTxt.setText(iwtb.getReferenceNumber());
                    altholder.binding.datetxt.setText(iwtb.getTransactionDate());
                    altholder.binding.amountTxt.setText("$ " + iwtb.getAmountDeposited());
                }
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    static class AmountLoadedTransactionsHolder extends RecyclerViewBaseAdapter.MyViewHolder {
        InteracAmountLoadedTransactionsAdapterviewBinding binding;

        private AmountLoadedTransactionsHolder(InteracAmountLoadedTransactionsAdapterviewBinding binding) {
            super(binding);
            this.binding = binding;
        }
    }
}
