package com.cxmax.library;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cxmax.library.binder.BinderNotFoundException;
import com.cxmax.library.binder.ItemViewBinder;
import com.cxmax.library.linker.DefaultLinker;
import com.cxmax.library.linker.Linker;
import com.cxmax.library.linker.OneToManyBuilder;
import com.cxmax.library.linker.OneToManyFlow;

import java.util.Collections;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-6.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements IMultiType{

    private static final String TAG = "MultiTypeAdapter";

    @NonNull private List<?> data;
    @NonNull private TypePool typePool;
    @Nullable protected LayoutInflater inflater;

    public MultiTypeAdapter() {
        this(Collections.emptyList());
    }

    public MultiTypeAdapter(@NonNull List<?> data) {
        this(data, new MultiTypePool());
    }

    public MultiTypeAdapter(@NonNull List<?> data, int capacity) {
        this(data, new MultiTypePool(capacity));
    }

    public MultiTypeAdapter(@NonNull List<?> data, @NonNull TypePool typePool) {
        this.data = data;
        this.typePool = typePool;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemViewBinder<?, ?> binder = typePool.getItemViewBinders().get(indexViewType);
        binder.setAdapter(this);
        assert inflater != null;
        return binder.onCreateViewHolder(inflater, parent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = data.get(position);
        ItemViewBinder binder = typePool.getItemViewBinders().get(holder.getItemViewType());
        binder.onBindViewHolder(holder, item, position);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        Object item = data.get(position);
        ItemViewBinder binder = typePool.getItemViewBinders().get(holder.getItemViewType());
        binder.onBindViewHolder(holder, item, payloads, position);
    }

    @Override
    public int getItemViewType(int position) {
        Object item = data.get(position);
        return indexInTypesOf(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public long getItemId(int position) {
        Object item = data.get(position);
        int itemViewType = getItemViewType(position);
        ItemViewBinder binder = typePool.getItemViewBinders().get(itemViewType);
        return binder.getItemId(item);
    }

    @Override
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        checkAndRemoveAllTypesIfNeed(clazz);
        typePool.register(clazz, binder, new DefaultLinker<T>());
    }

    @Override
    @CheckResult
    public <T> OneToManyFlow<T> register(@NonNull Class<? extends T> clazz) {
        checkAndRemoveAllTypesIfNeed(clazz);
        return new OneToManyBuilder<>(this, clazz);
    }

    @Override
    public void registerAll(@NonNull TypePool pool) {
        final int size = pool.getClasses().size();
        for (int i = 0; i < size; i++) {
            registerWithoutChecking(pool.getClasses().get(i), pool.getItemViewBinders().get(i), pool.getLinkers().get(i));
        }
    }

    public void setData(@NonNull List<?> data) {
        this.data = data;
    }

    @NonNull public List<?> getData() {
        return data;
    }

    public void setTypePool(@NonNull TypePool typePool) {
        this.typePool = typePool;
    }

    @NonNull public TypePool getTypePool() {
        return typePool;
    }

    private void checkAndRemoveAllTypesIfNeed(@NonNull Class<?> clazz) {
        if (!typePool.getClasses().contains(clazz)) {
            return;
        }
        Log.w(TAG, "You have registered the " + clazz.getSimpleName() + " type. " +
                "It will override the original binder(s).");
        while (true) {
            int index = typePool.getClasses().indexOf(clazz);
            if (index != -1) {
                typePool.getClasses().remove(index);
                typePool.getItemViewBinders().remove(index);
                typePool.getLinkers().remove(index);
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void registerWithoutChecking(@NonNull Class clazz, @NonNull ItemViewBinder itemViewBinder, @NonNull Linker linker) {
        checkAndRemoveAllTypesIfNeed(clazz);
        typePool.register(clazz, itemViewBinder, linker);
    }

    public <T> void registerWithLinker(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder, @NonNull Linker<T> linker) {
        typePool.register(clazz, binder, linker);
    }

    public int indexInTypesOf(@NonNull Object item) throws BinderNotFoundException {
        int index = typePool.firstIndexOf(item.getClass());
        if (index != -1) {
            @SuppressWarnings("unchecked")
            Linker<Object> linker = (Linker<Object>) typePool.getLinkers().get(index);
            return index + linker.index(item);
        }
        throw new BinderNotFoundException(item.getClass());
    }
}
