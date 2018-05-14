package com.latte.lib.launcher;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.util.timer.BaseTimeTask;
import com.example.latte_lib.R;

import java.time.temporal.ChronoUnit;


public class LauncherDelegate extends LatteDelegate implements BaseTimeTask.TimTaskUpdateListener, View.OnClickListener {

    AppCompatTextView tv_count;
    private int mTotalTime = 5;
    private int mInternalTime = 1;

    private BaseTimeTask mTimeTask;

    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        initTimerTask(rootView);
        tv_count.setOnClickListener(this);
    }

    private void initTimerTask(View rootView) {
        tv_count = rootView.findViewById(R.id.tv_count);
        mTimeTask = new BaseTimeTask(mTotalTime, mInternalTime);
        mTimeTask.setmTimTaskUpdateListener(this);
        mTimeTask.start();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void update(int totalTime, final int currentTime) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_count.setText("跳过\n" + currentTime +"s");
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopTimerTask();
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private void stopTimerTask() {
        if(mTimeTask != null){
            mTimeTask.quit();
            mTimeTask = null;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.tv_count){
            stopTimerTask();
            //跳转到新的Delegate
        }
    }
}
