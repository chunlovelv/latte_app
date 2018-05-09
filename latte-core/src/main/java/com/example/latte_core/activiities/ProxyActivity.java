package com.example.latte_core.activiities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.latte_core.R;
import com.example.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 代理activity
 */
public abstract class ProxyActivity extends SupportActivity {

    protected abstract LatteDelegate setLatteDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi")
        ContentFrameLayout container = new ContentFrameLayout(getApplicationContext());
        container.setId(R.id.delegate_container);
        //设置布局
        setContentView(container);
        //第一次加载布局
        if(savedInstanceState == null){
            loadRootFragment(R.id.delegate_container, setLatteDelegate());
        }
    }
}
