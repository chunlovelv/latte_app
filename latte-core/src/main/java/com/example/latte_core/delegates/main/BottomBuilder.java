package com.example.latte_core.delegates.main;

import com.example.latte_core.delegates.LatteDelegate;

import java.util.LinkedHashMap;

public final class BottomBuilder {

    private LinkedHashMap<BottomItem, BaseContentDelegate> mItems = new LinkedHashMap<>();

    static BottomBuilder builder(){
        return new BottomBuilder();
    }

    public BottomBuilder addItem(BottomItem item, BaseContentDelegate delegate){
        mItems.put(item, delegate);
        return this;
    }

    public BottomBuilder addItems(LinkedHashMap<BottomItem, BaseContentDelegate> items){
        mItems.putAll(items);
        return this;
    }

    public LinkedHashMap<BottomItem, BaseContentDelegate> build(){
        return mItems;
    }
}
