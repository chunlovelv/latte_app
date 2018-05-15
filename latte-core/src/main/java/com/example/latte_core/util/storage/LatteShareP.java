package com.example.latte_core.util.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;

import com.example.latte_core.app.Latte;

public class LatteShareP {

    private static final String LATTE_PROFILE = "latte_profile";
    private static final SharedPreferences SHARED_PREFERENCES =
            Latte.getApplicationContext().getSharedPreferences(LATTE_PROFILE, Context.MODE_PRIVATE);

    private static final String NAME = "name";
    private static final String IS_FIRST_IN = "IS_FIRST_IN" ;
    private static final String IS_LOGIN_IN = "IS_LOGIN_IN" ;

    private static LatteShareP INSTANCE = null;

    private LatteShareP(){

    }

    private LatteShareP getInstance(){
        if(INSTANCE == null){
            synchronized (LatteShareP.this){
                if(INSTANCE == null){
                    INSTANCE = new LatteShareP();
                }
            }
        }
        return INSTANCE;
    }

    public static boolean isFirstIn(){
        return SHARED_PREFERENCES.getBoolean(IS_FIRST_IN, true);
    }

    public static void setFirstIn(boolean flg){
        SHARED_PREFERENCES
                .edit()
                .putBoolean(IS_FIRST_IN, flg)
                .apply();
    }

    public static boolean isLogin(){
        return SHARED_PREFERENCES.getBoolean(IS_LOGIN_IN, false);
    }

    public static void setLoginIn(boolean flg){
        SHARED_PREFERENCES
                .edit()
                .putBoolean(IS_LOGIN_IN, flg)
                .apply();
    }


}
