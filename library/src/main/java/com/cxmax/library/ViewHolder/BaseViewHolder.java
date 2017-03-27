package com.cxmax.library.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/26.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void update(T t);
}
