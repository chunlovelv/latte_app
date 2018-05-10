package com.example.john.latte_ec;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.delegates.Delegate;
import com.example.latte_core.delegates.LatteDelegate;

public class IndexDelegate extends LatteDelegate {
    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
