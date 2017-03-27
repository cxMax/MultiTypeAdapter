package com.cxmax.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/27.
 */

public abstract class MultiTypeAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @NonNull protected Context context;
    @NonNull protected List<T> data;
    @NonNull protected MultiTypePool pool;

    public MultiTypeAdapter(@NonNull Context context,@NonNull List<T> data) {
        this.context = context;
        this.data = data;
        pool = new MultiTypePool();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return pool.onCreateViewHolder(parent , viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        pool.onBindViewHolder(data , holder , position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (pool.getProviderCount() <= 0) {
            return super.getItemViewType(position);
        }
        return pool.getItemViewType(data.get(position) , position);
    }
}
