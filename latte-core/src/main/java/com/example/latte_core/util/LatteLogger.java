package com.example.latte_core.util;

import com.orhanobut.logger.Logger;

public class LatteLogger {

    private static final int VERBOSE = 0;
    private static final int DEBUG = 1;
    private static final int WARN = 2;
    private static final int ERROR = 3;
    private static final int ASSERT = 4;
    private static final int LEVEL = 5;

    public static void v(String msg){
        if(LEVEL >VERBOSE) Logger.v(msg);
    }
    public static void d(String msg){
        if(LEVEL >DEBUG) Logger.d(msg);
    }
    public static void w(String msg){
        if(LEVEL >WARN) Logger.w(msg);
    }
    public static void e(String msg){
        if(LEVEL >ERROR) Logger.e(msg);
    }
    public static void i(String msg){
        if(LEVEL >ASSERT) Logger.i(msg);
    }
}
