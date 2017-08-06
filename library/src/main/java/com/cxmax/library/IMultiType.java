package com.cxmax.library;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.cxmax.library.binder.ItemViewBinder;
import com.cxmax.library.linker.OneToManyFlow;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-6.
 */

public interface IMultiType {

    /**
     * register one data to single {@link ItemViewBinder}
     * @param clazz
     * @param binder {@link ItemViewBinder}
     * @param <T> data
     */
    <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder);

    /**
     * register one data to multi {@link ItemViewBinder}
     * @param <T> data
     * @param clazz
     * @return
     */
    @CheckResult
    <T> OneToManyFlow<T> register(@NonNull Class<? extends T> clazz);

    /**
     * register all kinds of ViewType which in a new whole {@link MultiTypePool}
     * @param pool
     */
    void registerAll(@NonNull final TypePool pool);
}
