package com.example.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * fragment基类
 */
public abstract class Delegate extends SwipeBackFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Object o = setLayout();
        View rootView = null;
        if(o instanceof Integer){
            rootView = inflater.inflate((Integer) o, container, false);
        }else if(o instanceof View){
            rootView = (View) o;
        }else{
            throw new RuntimeException("setLayout is must int or View");
        }

        if(rootView != null){
            onBinderView(savedInstanceState, rootView);
        }

        return rootView;
    }

    /**
     * 子类通过此方法绑定
     * @param savedInstanceState
     * @param rootView
     */
    protected abstract void onBinderView(Bundle savedInstanceState, View rootView);

    /**
     * 子类通过此方法绑定视图
     * @return
     */
    public abstract Object setLayout();


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
