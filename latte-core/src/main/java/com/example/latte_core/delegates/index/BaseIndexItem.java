package com.example.latte_core.delegates.index;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.util.LinkedHashMap;

/**
 * 框架里面index界面的实体基类
 */
public class BaseIndexItem implements MultiItemEntity {

    //存放的是linkedHashMap
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEMS_QUEUE = new ReferenceQueue<>();
//    private final



    @Override
    public int getItemType() {
        return 0;
    }
}
