package com.cxmax.library.linker;

import android.support.annotation.NonNull;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-8-4.
 */

public interface OneToManyEndpoint<T> {

    void withLinker(@NonNull Linker<T> linker);

    void withClassLinker(@NonNull ClassLinker<T> classLinker);
}
