package com.example.latte_lib.delegates.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.latte_core.delegates.main.BaseContentDelegate;
import com.example.latte_lib.R;

public class IndexDelegate extends BaseContentDelegate {
    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
//        Glide.with(getContext()).load(null).into(null);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
