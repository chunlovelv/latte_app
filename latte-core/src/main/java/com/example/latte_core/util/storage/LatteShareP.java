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
        return SHARED_PREFERENCES.getBoolean(IS_FIRST_IN, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static void setFirstIn(boolean flg){
        SHARED_PREFERENCES
                .edit()
                .putBoolean(IS_FIRST_IN, flg)
                .apply();
    }


}
