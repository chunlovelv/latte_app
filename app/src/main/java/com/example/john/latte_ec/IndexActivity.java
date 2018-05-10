package com.example.john.latte_ec;

import com.example.latte_core.activiities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;

public class IndexActivity extends ProxyActivity {
    @Override
    protected LatteDelegate setLatteDelegate() {
        return new IndexDelegate();
    }
}
