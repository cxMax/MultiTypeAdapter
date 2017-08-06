package com.cxmax.library.linker;

import android.support.annotation.NonNull;

import com.cxmax.library.MultiTypeAdapter;
import com.cxmax.library.binder.ItemViewBinder;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-6.
 */

public class OneToManyBuilder<T> implements OneToManyFlow<T>, OneToManyEndpoint<T> {

    @NonNull private final MultiTypeAdapter adapter;
    @NonNull private final Class<? extends T> clazz;
    private ItemViewBinder<T, ?>[] binders;

    public OneToManyBuilder(@NonNull MultiTypeAdapter adapter, @NonNull Class<? extends T> clazz) {
        this.adapter = adapter;
        this.clazz = clazz;
    }

    @Override
    public void withLinker(@NonNull Linker<T> linker) {
        doRegister(linker);
    }

    @Override
    public void withClassLinker(@NonNull ClassLinker<T> classLinker) {
        doRegister(ClassLinkerWrapper.wrap(classLinker, binders));
    }

    @NonNull
    @Override
    public OneToManyEndpoint<T> to(@NonNull ItemViewBinder<T, ?>[] binders) {
        this.binders = binders;
        return this;
    }

    private void doRegister(@NonNull Linker<T> linker) {
        for (ItemViewBinder<T, ?> binder : binders) {
            adapter.registerWithLinker(clazz, binder, linker);
        }
    }
}
