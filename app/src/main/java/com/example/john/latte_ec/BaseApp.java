package com.example.john.latte_ec;

import android.support.multidex.MultiDexApplication;

import com.example.latte_core.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class BaseApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .configApiHost("http://127.0.0.1")
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
