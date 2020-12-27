package com.iq.interac.interacwallet.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.iq.interac.interacwallet.coreapp.RecyclerViewBaseAdapter;
import com.iq.interac.interacwallet.databinding.InteracAmountSentTransactionsAdaperviewBinding;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;
import com.iq.interac.interacwallet.util.MethodCallHelper;
import com.iq.interac.interacwallet.view.models.InteracWalletTransfersBean;

import androidx.annotation.NonNull;

public class InteracAmountSentActivityAdapter extends RecyclerViewBaseAdapter<InteracWalletTransfersBean> {
    Activity activity;

    public InteracAmountSentActivityAdapter(Context ct, Activity activity) {
        super(ct);
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerViewBaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InteracAmountSentTransactionsAdaperviewBinding binding = InteracAmountSentTransactionsAdaperviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AmountSentTransactionsHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBaseAdapter.MyViewHolder holder, int position) {
        try {
            if (holder instanceof AmountSentTransactionsHolder) {
                AmountSentTransactionsHolder astholder = (AmountSentTransactionsHolder) holder;
                InteracWalletTransfersBean iwtb = getItem(position);

                if (iwtb != null) {
                    astholder.binding.sentToNameTxt.setText(iwtb.getReceivingCustName());
                    astholder.binding.sentToPhoneTxt.setText(iwtb.getReceivingPhoneNumber());
                    astholder.binding.sentDateTxt.setText(iwtb.getTransactionDate());
                    astholder.binding.amountSentTxt.setText(MethodCallHelper.getInstance(activity).getCurrencySymbol() + " " + iwtb.getSentAmount());
                }
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    static class AmountSentTransactionsHolder extends RecyclerViewBaseAdapter.MyViewHolder {
        InteracAmountSentTransactionsAdaperviewBinding binding;

        private AmountSentTransactionsHolder(InteracAmountSentTransactionsAdaperviewBinding binding) {
            super(binding);
            this.binding = binding;
        }
    }
}
