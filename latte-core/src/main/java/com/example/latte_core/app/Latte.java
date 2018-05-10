package com.example.latte_core.app;

import android.content.Context;

import java.util.HashMap;

public final class Latte {

    /**
     * 初始化
     * @param context
     * @return
     */
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),
                context.getApplicationContext());
        return Configurator.newInstance();
    }

    public static HashMap<String , Object> getConfigurations(){
        return Configurator.newInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
