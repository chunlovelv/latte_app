package com.example.latte_core.delegates.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.latte_core.R;
import com.example.latte_core.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseMainDelegate extends LatteDelegate implements View.OnClickListener {
    private List<BottomItem> mBottomItems = new ArrayList<>();
    private List<LatteDelegate> mLatteDelegates = new ArrayList<>();
    private LinkedHashMap<BottomItem, BaseContentDelegate> mLinkedHashMap = new LinkedHashMap<>();
    private int mClickColor = Color.RED;
    private int mCurrentDelegate = 0;

    protected abstract int setClickColor();

    protected abstract int setCurrentDelegate();

    protected abstract LinkedHashMap<BottomItem, BaseContentDelegate> setDelegates(BottomBuilder builder);


    private LinearLayoutCompat mBottomContainer;
    private ContentFrameLayout mDelegateContainer;

    @Override
    public Object setLayout() {
        return R.layout.delegate_base_container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置当前的点击颜色
        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }

        //设置当前的选中的delegate
        mCurrentDelegate = setCurrentDelegate();

        BottomBuilder bottomBuilder = BottomBuilder.builder();
        mLinkedHashMap = setDelegates(bottomBuilder);

        for (Map.Entry<BottomItem, BaseContentDelegate> entry : mLinkedHashMap.entrySet()) {
            mBottomItems.add(entry.getKey());
            mLatteDelegates.add(entry.getValue());
        }
    }

    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        initView(rootView);

        final int size = mBottomItems.size();
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        RelativeLayout r = null;
        IconTextView itv;
        AppCompatTextView t;
        for (int i = 0; i < size; i++) {
            //将item布局加载到容器中去
            layoutInflater.inflate(R.layout.layout_bottom_item, mBottomContainer);
            r = (RelativeLayout) mBottomContainer.getChildAt(i);
            r.setOnClickListener(this);
            r.setTag(i);
            itv = (IconTextView) r.getChildAt(0);
            t = (AppCompatTextView) r.getChildAt(1);
            BottomItem item = mBottomItems.get(i);
            itv.setText(item.getIcon());
            t.setText(item.getIconName());
            if (mCurrentDelegate == i) {
                itv.setTextColor(mClickColor);
                t.setTextColor(mClickColor);
            }
        }

//        mLatteDelegates.toArray(new Support)
        final SupportFragment[] sfs = mLatteDelegates.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.delagate_container, mCurrentDelegate, sfs);
    }

    private void initView(View rootView) {
        mBottomContainer = rootView.findViewById(R.id.bottom_container);
        mDelegateContainer = rootView.findViewById(R.id.delagate_container);
    }


    private void resetColor(){
        final int childCount = mBottomContainer.getChildCount();
        for(int i = 0; i<childCount;i++){
            final RelativeLayout r = (RelativeLayout) mBottomContainer.getChildAt(i);
            IconTextView itv = (IconTextView) r.getChildAt(0);
            AppCompatTextView t = (AppCompatTextView) r.getChildAt(1);
            itv.setTextColor(Color.GRAY);
            t.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        final  int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout r = (RelativeLayout) mBottomContainer.getChildAt(tag);
        IconTextView itv = (IconTextView) r.getChildAt(0);
        AppCompatTextView t = (AppCompatTextView) r.getChildAt(1);
        itv.setTextColor(mClickColor);
        t.setTextColor(mClickColor);

        showHideFragment(mLatteDelegates.get(tag), mLatteDelegates.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }
}
