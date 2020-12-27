package com.iq.interac.interacwallet.coreapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.iq.interac.interacwallet.R;
import com.iq.interac.interacwallet.functions.ScreenNavKeys;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;


public class RecyclerViewBaseAdapter<T> extends RecyclerView.Adapter<RecyclerViewBaseAdapter.MyViewHolder> {
    protected final LayoutInflater mInflater;
    private final Context context;
    private List<T> mItemsList;
    private int lastPosition = 1;

    // Constructors
    protected RecyclerViewBaseAdapter(Context context) {
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected RecyclerViewBaseAdapter(Context context, List<T> itemsList) {
        this.context = context;
        this.mItemsList = itemsList;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // region BaseAdapter Methods
    @Override
    public int getItemCount() {
        return (mItemsList == null ? 0 : mItemsList.size());
    }

    protected T getItem(int position) {
        if (mItemsList == null || position < 0 || position >= getItemCount()) {
            return null;
        } else {
            return mItemsList.get(position);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected List<T> getList() {
        return mItemsList;
    }

    // Public Methods
    public void setList(List<T> list) {
        mItemsList = list;
        notifyDataSetChanged();
    }

    public void delete(T item) {
        delete(item, true);
    }

    private void delete(T item, boolean notifyDataSetChanged) {
        boolean deleteSuccessful = false;
        if (mItemsList != null) {
            deleteSuccessful = mItemsList.remove(item);
        }

        if (deleteSuccessful && notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (mItemsList != null) {
            mItemsList.clear();
            notifyDataSetChanged();
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void setAnimation(Context context, View viewToAnimate, int position) {
        try {
            // If the bound view wasn't previously displayed on screen, it's animated
            if (position > lastPosition) {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.top_from_bottom);
                viewToAnimate.startAnimation(animation);
                lastPosition = position;
            }
        } catch (Exception e) {
            if (ScreenNavKeys.printException) {
                e.printStackTrace();
                //FirebaseCrashlytics.getInstance().recordException(e);
            }
        }
    }

    // Adapter View Holder Class
    protected static class MyViewHolder extends RecyclerView.ViewHolder {
        /*public MyViewHolder(View itemView) {
            super(itemView);
        }*/
        public MyViewHolder(ViewBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
