package com.cxmax.library.linker;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.cxmax.library.binder.ItemViewBinder;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-4.
 */

public interface OneToManyFlow<T> {

    @NonNull
    @CheckResult
    OneToManyEndpoint<T> to(@NonNull ItemViewBinder<T, ?>... binders);

}
