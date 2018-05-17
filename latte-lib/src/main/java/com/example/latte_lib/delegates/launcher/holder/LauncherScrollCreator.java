package com.example.latte_lib.delegates.launcher.holder;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class LauncherScrollCreator implements CBViewHolderCreator<LauncherScrollHolder> {

    @Override
    public LauncherScrollHolder createHolder() {
        return new LauncherScrollHolder();
    }
}
