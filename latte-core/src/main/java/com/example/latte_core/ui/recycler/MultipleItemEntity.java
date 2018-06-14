package com.example.latte_core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.latte_core.delegates.index.MultipleFields;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * 作者: 李纯
 * 时间: 2018/6/14
 * 说明: 将jsonData数据根据数据类型来封装
 */
public class MultipleItemEntity implements MultiItemEntity {
    //缓存队列（当系统内存不足的时候，SoftReference会将数据放到此对象中）
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    //数据存放集合
    private final LinkedHashMap<Object, Object> MULTY_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTY_FIELDS, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> field){
        FIELDS_REFERENCE.get().putAll(field);
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    /**
     * 通过key来获取value
     * @param key
     * @param <T>
     * @return 不需要转换成任何类型
     */
    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }


    /**
     * 获取所有的属性值
     * @return
     */
    public final LinkedHashMap<Object, Object> getFields(){
        return FIELDS_REFERENCE.get();
    }

    /**
     * 添加属性
     * @param key
     * @param value
     * @return
     */
    public MultipleItemEntity addField(Object key, Object value){
        MULTY_FIELDS.put(key, value);
        return this;
    }

    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }


    public static final class MultipleItemEntityBuilder{

        private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();
        MultipleItemEntityBuilder(){
            //每次new的时候都将之前的数据清空
            FIELDS.clear();
        }

        public MultipleItemEntityBuilder setItemType(int itemType){
            FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
            return this;
        }

        public MultipleItemEntityBuilder setField(Object key, Object value){
            FIELDS.put(key, value);
            return this;
        }

        public MultipleItemEntityBuilder setFields(LinkedHashMap<Object, Object> map){
            FIELDS.putAll(map);
            return this;
        }


        public final static MultipleItemEntity build(){
            return new MultipleItemEntity(FIELDS);
        }
    }
}
