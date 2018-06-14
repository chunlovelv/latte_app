package com.example.latte_lib.delegates.main.index;

import android.text.TextUtils;
import android.view.TextureView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.delegates.index.MultipleFields;
import com.example.latte_core.ui.recycler.DataConverter;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 作者: 李纯
 * 时间: 2018/6/14
 * 说明:
 */
public class IndexDataConverter extends DataConverter {
    @Override
    protected ArrayList<MultipleItemEntity> converter() {
        final JSONArray jsonArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        if(jsonArray != null){
            final int jsonArrSize = jsonArray.size();
            for(int i = 0;i< jsonArrSize;i++){
                JSONObject item = jsonArray.getJSONObject(i);
                int id = item.getIntValue("goodsId");
                int spanSize = item.getIntValue("spanSize");
                String text = item.getString("text");
                String imageUrl = item.getString("imageUrl");
                JSONArray bannersArray = item.getJSONArray("banners");
                 ArrayList<String> bannerImgUrls = new ArrayList<>();
                int itemType = 0;
                //都是空，就是banners的
                if(TextUtils.isEmpty(text) && TextUtils.isEmpty(imageUrl)){
                    itemType = ItemType.BANNERS;
                    final int size = bannersArray.size();
                    for (int j = 0;j<size;j++){
                        bannerImgUrls.add(bannersArray.getString(i));
                    }
                }else if(!TextUtils.isEmpty(text) && TextUtils.isEmpty(imageUrl)){
                    itemType = ItemType.TEXT;
                }else if(!TextUtils.isEmpty(text) && !TextUtils.isEmpty(imageUrl)){
                    itemType = ItemType.TEXT_IMAGE;
                }else if(TextUtils.isEmpty(text) && !TextUtils.isEmpty(imageUrl)){
                    itemType = ItemType.IMAGE;
                }

                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(itemType)
                        .setField(MultipleFields.ID, id)
                        .setField(MultipleFields.SPAN_SIZE, spanSize)
                        .setField(MultipleFields.TEXT, text)
                        .setField(MultipleFields.IMAGE_URL, imageUrl)
                        .setField(MultipleFields.BANNERS, bannerImgUrls)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
