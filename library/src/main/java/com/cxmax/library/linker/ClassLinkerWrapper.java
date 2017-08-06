package com.cxmax.library.linker;

import android.support.annotation.NonNull;

import com.cxmax.library.binder.ItemViewBinder;

import java.util.Arrays;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-6.
 */

public class ClassLinkerWrapper<T> implements Linker<T> {

    @NonNull private final ClassLinker<T> classLinker;
    @NonNull private ItemViewBinder<T, ?>[] binders;

    public ClassLinkerWrapper(@NonNull ClassLinker<T> classLinker, @NonNull ItemViewBinder<T, ?>[] binders) {
        this.classLinker = classLinker;
        this.binders = binders;
    }

    @NonNull
    static <T> ClassLinkerWrapper<T> wrap(@NonNull ClassLinker<T> classLinker, @NonNull ItemViewBinder<T, ?>[] binders) {
        return new ClassLinkerWrapper<T>(classLinker, binders);
    }

    /**
     * Returns the index of your registered {@link ItemViewBinder} for your item.
     *
     * @param t item data
     * @return The index of your registered {@link ItemViewBinder}
     */
    @Override
    public int index(@NonNull T t) {
        Class<?> userIndexClass = classLinker.index(t);
        for (int i = 0; i < binders.length; i++) {
            if (binders[i].getClass().equals(userIndexClass)) {
                return i;
            }
        }
        throw new IndexOutOfBoundsException(
                String.format("%s is out of your registered binders'(%s) bounds.",
                        userIndexClass.getName(), Arrays.toString(binders))
        );
    }
}
