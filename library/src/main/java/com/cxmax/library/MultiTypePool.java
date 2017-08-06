package com.cxmax.library;

import android.support.annotation.NonNull;

import com.cxmax.library.binder.ItemViewBinder;
import com.cxmax.library.linker.Linker;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-6.
 */

public class MultiTypePool implements TypePool {

    private @NonNull final List<Class<?>> classes;
    private @NonNull final List<ItemViewBinder<?, ?>> binders;
    private @NonNull final List<Linker<?>> linkers;

    public MultiTypePool() {
        this.classes = new ArrayList<>();
        this.binders = new ArrayList<>();
        this.linkers = new ArrayList<>();
    }

    public MultiTypePool(int capacity) {
        this.classes = new ArrayList<>(capacity);
        this.binders = new ArrayList<>(capacity);
        this.linkers = new ArrayList<>(capacity);
    }

    public MultiTypePool(@NonNull List<Class<?>> classes, @NonNull List<ItemViewBinder<?, ?>> binders, @NonNull List<Linker<?>> linkers) {
        this.classes = classes;
        this.binders = binders;
        this.linkers = linkers;
    }

    @Override
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder, @NonNull Linker<T> linker) {
        classes.add(clazz);
        binders.add(binder);
        linkers.add(linker);
    }

    @Override
    public int firstIndexOf(@NonNull Class<?> clazz) {
        int index = classes.indexOf(clazz);
        if (index != -1) {
            return index;
        }
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }
        return -1;
    }

    @NonNull
    @Override
    public List<Class<?>> getClasses() {
        return classes;
    }

    @NonNull
    @Override
    public List<ItemViewBinder<?, ?>> getItemViewBinders() {
        return binders;
    }

    @NonNull
    @Override
    public List<Linker<?>> getLinkers() {
        return linkers;
    }
}
