package com.example.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

public class Configurator {

    //配置信息相关
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();

    public static Configurator newInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }


    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    //线程安全的懒汉模式单例
    private final static  class Holder{
        private static final  Configurator INSTANCE = new Configurator();
    }

    /**
     * 配置完成
     */
    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    /**
     * 开始配置项目信息
     */
    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    /**
     * 配置host
     * @param host
     * @return
     */
    public final Configurator configApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 检查当前配置是否完成
     */
    public final void checkConfigReady(){
        boolean ready = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!ready){
            throw new RuntimeException("configration is not success!");
        }
    }

    /**
     *
     * @param key
     * @param <T>
     * @return
     */
    final <T> T getConfigration(Enum<ConfigType> key){
        checkConfigReady();
        return (T) LATTE_CONFIGS.get(key.name());
    }


    /**
     * 图标的配置
     */
    private void initIcons(){
        if(ICONS.size() > 0){
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i = 1; i<ICONS.size();i++){
                Iconify.with(ICONS.get(i));
            }
        }
    }

    public Configurator withIcon(IconFontDescriptor iconFontDescriptor){
//        Iconify.with(iconFontDescriptor);
        ICONS.add(iconFontDescriptor);
        return this;
    }
}
