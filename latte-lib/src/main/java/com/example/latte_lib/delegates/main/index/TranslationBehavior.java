package com.example.latte_lib.delegates.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte_core.app.Latte;
import com.example.latte_core.util.LatteLogger;
import com.example.latte_lib.R;

/**
 * 作者: 李纯
 * 时间: 2018/6/15
 * 说明:
 */
public class TranslationBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    private int normal_color = Color.TRANSPARENT;
    private int scroll_color = Color.YELLOW;
    private int scrollY = 20;
    public TranslationBehavior() {
    }

    public TranslationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout,
                                  Toolbar child,
                                  View target,
                                  int dx,
                                  int dy,
                                  int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout,child,target,dx,dy,consumed);
        Log.d("TranslationBehavior", "dy==> "+dy);
        scrollY += dy;
        if(scrollY > 50){
            child.setBackgroundColor(scroll_color);
        }else {
            child.setBackgroundColor(normal_color);
        }
    }

}
