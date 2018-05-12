package com.example.john.latte_ec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.latte_core.activiities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;
import com.latte.lib.launcher.LauncherDelegate;

public class TestActivity extends ProxyActivity {

    @Override
    protected LatteDelegate setLatteDelegate() {
        return new LauncherDelegate();
    }
}
