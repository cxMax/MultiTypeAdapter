package com.cxmax.library.linker;

import android.support.annotation.NonNull;

import com.cxmax.library.binder.ItemViewBinder;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-4.
 */

public interface ClassLinker<T> {

    /**
     * Returns the class of your registered {@link ItemViewBinder} for your item.
     * @param t item data
     * @return The index of your registered {@link ItemViewBinder}
     */
    @NonNull
    Class<? extends ItemViewBinder<T, ?>> index(@NonNull T t);

}
