package com.cxmax.library.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cxmax.library.MultiTypeAdapter;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-6.
 */

public abstract class ItemViewBinder<T, VH extends RecyclerView.ViewHolder> {

    private MultiTypeAdapter adapter;

    @NonNull
    public abstract VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    public abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item, int pos);

    public void onBindViewHolder(@NonNull VH holder, @NonNull T item, @NonNull List<Object> payloads, int pos) {
        onBindViewHolder(holder, item, pos);
    }

    public final int getPosition(@NonNull final RecyclerView.ViewHolder holder) {
        return holder.getAdapterPosition();
    }

    public long getItemId(@NonNull T item) {
        return RecyclerView.NO_ID;
    }

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }
}
