package com.example.latte_lib.delegates.main.index;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.latte_core.app.Latte;
import com.example.latte_core.util.LatteLogger;
import com.example.latte_lib.R;

/**
 * 作者: 李纯
 * 时间: 2018/6/15
 * 说明:
 */
public class TranslationBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    public TranslationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        LatteLogger.d("dependency.getName==> "+dependency.getClass().getName());
        LatteLogger.d("child.getName==> "+child.getClass().getName());
        return dependency.getId()== R.id.list;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        if(dependency.getScaleY() > 10){
            child.setBackgroundColor(0xffffffff);
        }
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull Toolbar child, @NonNull View target,
                                  int dx, int dy,
                                  @NonNull int[] consumed, int type) {
//        super.onNestedPreScroll(coordinatorLayout,child,target,dx,dy,consumed,type);
        LatteLogger.d("dy==> "+dy);
    }
}
