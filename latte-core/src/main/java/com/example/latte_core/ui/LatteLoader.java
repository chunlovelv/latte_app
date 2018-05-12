package com.example.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.latte_core.R;
import com.example.latte_core.utils.DimenUtil;

import java.util.ArrayList;

public class LatteLoader {
    private static final int LOAD_SIZE_SCALE = 8;
    private static final int LOAD_SIZE_OFFSET = 10;

    private static final ArrayList<AppCompatDialog> DIALOG_ARRAY_LIST = new ArrayList<>();

    public static void showLoading(Context context, AVLoadingIndicators indicators) {
        showLoading(context, indicators, 0xffffffff);
    }

    public static void showLoading(Context context, AVLoadingIndicators indicators, int loadingColor) {
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.load_dialog_style);
        dialog.setContentView(LatteLoaderCreator.create(indicators, context, loadingColor));
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = DimenUtil.getScreenWidth() / LOAD_SIZE_SCALE;
            lp.height = DimenUtil.getScreenHeight() / LOAD_SIZE_SCALE;
            lp.height = lp.height + DimenUtil.getScreenHeight() / LOAD_SIZE_OFFSET;
            lp.gravity = Gravity.CENTER;
        }
        DIALOG_ARRAY_LIST.add(dialog);
        dialog.show();
    }

    public static void cancel(){
        for(AppCompatDialog dialog: DIALOG_ARRAY_LIST){
            if(dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
        DIALOG_ARRAY_LIST.clear();
    }
}
