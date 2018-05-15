package com.latte.lib.launcher;

import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.util.storage.LatteShareP;
import com.example.latte_lib.R;
import com.example.latte_lib.login.LoginDelegate;
import com.latte.lib.launcher.holder.LauncherScrollCreator;

import java.util.ArrayList;
import java.util.List;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    ConvenientBanner<Integer> mBanner;
    private List<Integer> mBannerImages = new ArrayList<>();

    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        if(LatteShareP.isFirstIn()){
            LatteShareP.setFirstIn(false);
        }
    }

    @Override
    public Object setLayout() {
        initBanner();
        return mBanner;
    }

    private void initBanner() {
        mBannerImages.add(R.mipmap.launcher_01);
        mBannerImages.add(R.mipmap.launcher_02);
        mBannerImages.add(R.mipmap.launcher_03);
        mBannerImages.add(R.mipmap.launcher_04);
        mBannerImages.add(R.mipmap.launcher_05);
        mBanner = new ConvenientBanner<>(getActivity());
        mBanner.setCanLoop(false);
        mBanner.setPageIndicator(new int[]{R.drawable.shap_scroll_point_normal,
                R.drawable.shap_scroll_point_selected});
        mBanner.setPages(new LauncherScrollCreator(), mBannerImages);
        mBanner.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        if(position == mBannerImages.size() -1){
            startWithPop(new LoginDelegate());
        }
    }
}
