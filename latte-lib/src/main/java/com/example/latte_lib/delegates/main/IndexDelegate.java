package com.example.latte_lib.delegates.main;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.delegates.main.BaseContentDelegate;
import com.example.latte_lib.R;

public class IndexDelegate extends BaseContentDelegate {
    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}