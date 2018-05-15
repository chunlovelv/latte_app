package com.latte.lib.launcher.holder;

import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.latte_lib.R;

public class LauncherScrollCreator implements CBViewHolderCreator<LauncherScrollHolder> {

    @Override
    public LauncherScrollHolder createHolder() {
        return new LauncherScrollHolder();
    }
}
