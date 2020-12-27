package com.iq.interac.interacwallet.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iq.interac.interacwallet.coreapp.RecyclerViewBaseAdapter;
import com.iq.interac.interacwallet.databinding.InteracAmountReceivedTransactionsAdapterviewBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.models.InteracWalletTransfersBean;

import androidx.annotation.NonNull;

public class InteracAmountReceivedActivityAdapter extends RecyclerViewBaseAdapter<InteracWalletTransfersBean> {
    Activity activity = null;

    public InteracAmountReceivedActivityAdapter(Context ct, Activity activity) {
        super(ct);
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerViewBaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InteracAmountReceivedTransactionsAdapterviewBinding binding = InteracAmountReceivedTransactionsAdapterviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AmountReceivedTransactionsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBaseAdapter.MyViewHolder holder, int position) {
        try {
            if (holder instanceof AmountReceivedTransactionsHolder) {
                AmountReceivedTransactionsHolder artholder = (AmountReceivedTransactionsHolder) holder;
                InteracWalletTransfersBean iwtb = getItem(position);

                if (iwtb != null) {
                    artholder.binding.amountReceivedTxt.setText(MethodCallHelper.getInstance(activity).getCurrencySymbol() + " " + iwtb.getSentAmount());
                    artholder.binding.receivedFromNameTxt.setText(iwtb.getSendingCustName());
                    artholder.binding.receivedFromPhoneTxt.setText(iwtb.getSendingPhoneNumber());
                    artholder.binding.receivedDateTxt.setText(iwtb.getTransactionDate());
                }
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    static class AmountReceivedTransactionsHolder extends RecyclerViewBaseAdapter.MyViewHolder {
        InteracAmountReceivedTransactionsAdapterviewBinding binding;

        private AmountReceivedTransactionsHolder(InteracAmountReceivedTransactionsAdapterviewBinding binding) {
            super(binding);
            this.binding = binding;
        }
    }
}