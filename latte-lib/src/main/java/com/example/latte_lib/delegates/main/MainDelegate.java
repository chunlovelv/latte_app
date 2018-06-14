package com.example.latte_lib.delegates.main;

import com.example.latte_core.delegates.main.BaseMainDelegate;
import com.example.latte_core.delegates.main.BaseContentDelegate;
import com.example.latte_core.delegates.main.BottomBuilder;
import com.example.latte_core.delegates.main.BottomItem;
import com.example.latte_lib.delegates.main.index.IndexDelegate;

import java.util.LinkedHashMap;

public class MainDelegate extends BaseMainDelegate {
    @Override
    protected int setClickColor() {
        return 0;
    }

    @Override
    protected int setCurrentDelegate() {
        return 0;
    }

    @Override
    protected LinkedHashMap<BottomItem, BaseContentDelegate> setDelegates(BottomBuilder builder) {
        builder.addItem(new BottomItem("{fa-home}","首页"),new IndexDelegate());
        builder.addItem(new BottomItem("{fa-sort}","分类"),new SortDelegate());
        builder.addItem(new BottomItem("{fa-compass}","发现"),new CompassDelegate());
        builder.addItem(new BottomItem("{fa-shopping-cart}","购物车"),new ShoppingCarDelegate());
        builder.addItem(new BottomItem("{fa-user}","我的"),new UserDelegate());
        return builder.build();
    }
}
