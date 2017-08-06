package com.cxmax.library.binder;

import android.support.annotation.NonNull;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-4.
 */

public class BinderNotFoundException extends RuntimeException {

    public BinderNotFoundException(@NonNull Class<?> clazz) {
        super("Do you have registered the binder for {className}.class in the adapter/pool?"
                .replace("{className}", clazz.getSimpleName()));
    }

}
