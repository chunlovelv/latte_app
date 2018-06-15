package com.example.latte_lib.delegates.main.index;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte_core.delegates.index.MultipleFields;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.utils.ImageUtil;
import com.example.latte_lib.R;
import com.example.latte_lib.delegates.utils.BannerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 李纯
 * 时间: 2018/6/15
 * 说明:
 */
public class DataAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, BaseViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup {
    public DataAdapter(@Nullable List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_text_image);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.BANNERS, R.layout.item_multiple_banner);

        setSpanSizeLookup(this);
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder holder, MultipleItemEntity item) {
        String text = item.getField(MultipleFields.TEXT);
        String imageUrl = item.getField(MultipleFields.IMAGE_URL);
        ArrayList<String> urls = item.getField(MultipleFields.BANNERS);
        switch (holder.getItemViewType()){
            case ItemType.TEXT:
                holder.setText(R.id.tv_multiple_text, text);
                break;
            case ItemType.TEXT_IMAGE:
                ImageUtil.loadImage(imageUrl, (ImageView) holder.getView(R.id.img_multiple_image));
                holder.setText(R.id.tv_multiple_text, text);
                break;
            case ItemType.IMAGE:
                ImageUtil.loadImage(imageUrl, (ImageView) holder.getView(R.id.img_multiple_image));
                break;
            case ItemType.BANNERS:
                ConvenientBanner banner = holder.getView(R.id.img_containner);
                BannerUtil.setBanner(banner, urls);
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
