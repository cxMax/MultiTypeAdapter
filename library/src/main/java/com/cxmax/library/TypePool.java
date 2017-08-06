package com.cxmax.library;

import android.support.annotation.NonNull;

import com.cxmax.library.binder.ItemViewBinder;
import com.cxmax.library.linker.Linker;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-6.
 */

public interface TypePool {

    <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder, @NonNull Linker<T> linker);

    int firstIndexOf(@NonNull Class<?> clazz);

    @NonNull List<Class<?>> getClasses();

    @NonNull List<ItemViewBinder<?, ?>> getItemViewBinders();

    @NonNull List<Linker<?>> getLinkers();

}
