package com.cxmax.library.linker;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.cxmax.library.MultiTypeAdapter;
import com.cxmax.library.binder.ItemViewBinder;

/**
 * @describe : 绑定 data 和 {@link ItemViewBinder} , 这个主要的作用是对一对多样式的拓展以及具体一对多绑定逻辑,
 *             如果是单独一对一的样式, 那么则无需关心这个package里面的类,
 *             因为 {@link MultiTypeAdapter} onCreateViewHolder ,onBindViewHolder
 *             的具体实现都是交给了 {@link ItemViewBinder}
 *
 * @usage :
 * <p>
 * 如果是单个data绑定单个 {@link ItemViewBinder} , 那么默认直接返回 {@link DefaultLinker} ,index 函数返回 0 , 表示Data-to-ItemViewBinder唯一绑定关系
 *
 * 如果是单个data绑定多个 {@link ItemViewBinder} , 那么按照注册的先后顺序, 返回已注册{@link ItemViewBinder}个数区间范围,
 * 从而根据index再去设定具体Data-to-ItemViewBinder的对应规则;
 * @see OneToManyFlow#to(ItemViewBinder[])
 * @see OneToManyEndpoint#withLinker(Linker)
 * </p>
 * Created by caixi on 17-8-6.
 */

public interface Linker<T> {

    /**
     * Returns the index of your registered {@link ItemViewBinder} for your item.
     * @param t item data
     * @return The index of your registered {@link ItemViewBinder}
     */
    @IntRange(from = 0) int index(@NonNull T t);
}
