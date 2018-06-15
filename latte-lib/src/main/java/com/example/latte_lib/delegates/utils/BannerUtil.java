package com.example.latte_lib.delegates.utils;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.latte_core.utils.ImageUtil;
import com.example.latte_lib.R;

import java.util.List;

/**
 * 作者: 李纯
 * 时间: 2018/6/15
 * 说明:
 */
public class BannerUtil {

    public static ConvenientBanner setBanner(ConvenientBanner banner, List<String> urls){
        banner.setCanLoop(true);
        banner.startTurning(3000);
        banner.setScrollDuration(2000);
        banner.setPageIndicator(new int[]{R.drawable.shap_scroll_point_normal, R.drawable.shap_scroll_point_selected});
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new Holder<String>() {
                    AppCompatImageView mAppCompatImageView;
                    @Override
                    public View createView(Context context) {
                        mAppCompatImageView = new AppCompatImageView(context);
                        return mAppCompatImageView;
                    }

                    @Override
                    public void UpdateUI(Context context, int position, String data) {
                        ImageUtil.loadImage(data, mAppCompatImageView);
                    }
                };
            }
        }, urls);

        return banner;
    }
}
