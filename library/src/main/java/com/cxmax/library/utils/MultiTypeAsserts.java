package com.cxmax.library.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.cxmax.library.MultiTypeAdapter;
import com.cxmax.library.binder.BinderNotFoundException;

import java.util.List;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-4.
 */

public class MultiTypeAsserts {

    private MultiTypeAsserts() {
        throw new AssertionError();
    }

    public static void assertAllRegistered(@NonNull MultiTypeAdapter adapter, @NonNull List<?> items) throws BinderNotFoundException, IllegalArgumentException, IllegalAccessError {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Your Items/List is empty.");
        }
        for (Object item : items) {
            adapter.indexInTypesOf(item);
        }
    }

    public static void assertHasTheSameAdapter(@NonNull RecyclerView recyclerView, @NonNull MultiTypeAdapter adapter) throws IllegalArgumentException, IllegalAccessError {
        if (recyclerView.getAdapter() == null) {
            throw new IllegalAccessError("The assertHasTheSameAdapter() method must " +
                    "be placed after recyclerView.setAdapter()");
        }
        if (recyclerView.getAdapter() != adapter) {
            throw new IllegalArgumentException(
                    "Your recyclerView's adapter is not the sample with the argument adapter.");
        }
    }
}
