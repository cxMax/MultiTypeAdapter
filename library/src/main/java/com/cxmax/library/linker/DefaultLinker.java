package com.cxmax.library.linker;

import android.support.annotation.NonNull;


/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-4.
 */

public class DefaultLinker<T> implements Linker<T> {

    /**
     *
     * @param t item data
     * @return 0 表示Data-to-ItemViewBinder唯一绑定关系
     */
    @Override
    public int index(@NonNull T t) {
        return 0;
    }
}
