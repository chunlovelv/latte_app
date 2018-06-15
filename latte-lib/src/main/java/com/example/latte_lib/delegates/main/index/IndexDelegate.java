package com.example.latte_lib.delegates.main.index;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.latte_core.app.UrlConfig;
import com.example.latte_core.delegates.index.MultipleFields;
import com.example.latte_core.delegates.main.BaseContentDelegate;
import com.example.latte_core.net.HttpMethod;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccessCallBack;
import com.example.latte_core.status.StatusBarCompat;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.util.LatteLogger;
import com.example.latte_lib.R;

import java.util.ArrayList;

public class IndexDelegate extends BaseContentDelegate implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView mRecyclerView;
    private DataAdapter mDataAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private static Handler mHandler = new Handler();

    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        initView(rootView);
        RestClient.builder()
                .url(UrlConfig.JSONURL)
                .method(HttpMethod.GET)
                .success(new ISuccessCallBack() {
                    @Override
                    public void success(String result) {
                        IndexDataConverter converter = new IndexDataConverter();
                        converter.setJsonData(result);
                        ArrayList<MultipleItemEntity> list = converter.converter();
                        mDataAdapter = new DataAdapter(list);
                        mRecyclerView.setAdapter(mDataAdapter);

                    }
                }).build().get();
    }

    private void initView(View rootView) {
        StatusBarCompat.translucentStatusBar(getActivity());
        mSwipeRefreshLayout = rootView.findViewById(R.id.layout_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = rootView.findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onRefresh() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
