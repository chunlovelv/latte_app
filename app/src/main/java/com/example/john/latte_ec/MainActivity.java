package com.example.john.latte_ec;

import com.example.latte_core.activiities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_lib.delegates.launcher.LauncherDelegate;
import com.example.latte_lib.delegates.main.MainDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected LatteDelegate setLatteDelegate() {
        return new MainDelegate();
    }
}
