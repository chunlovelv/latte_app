package com.example.latte_core.util.timer;

import android.os.HandlerThread;
import android.os.SystemClock;

public class BaseTimeTask extends HandlerThread {
    private static final String TAG = "BaseTimeTask";
    private int mTotalTime, mInternalTime;

    public void setmTimTaskUpdateListener(TimTaskUpdateListener timTaskUpdateListener) {
        this.mTimTaskUpdateListener = timTaskUpdateListener;
    }

    private TimTaskUpdateListener mTimTaskUpdateListener;

    public BaseTimeTask(int totalTime,//计数为秒
                        int internalTime//计数为秒
    ) {
        super(TAG);
        this.mInternalTime = internalTime * 1000;
        this.mTotalTime = totalTime * 1000;
    }

    @Override
    protected void onLooperPrepared() {
        int temp = mTotalTime;
        while (temp >= 0) {
            if (mTimTaskUpdateListener != null) {
                mTimTaskUpdateListener.update(mTotalTime / 1000, temp /1000);
            }
            SystemClock.sleep(mInternalTime);
            temp -= mInternalTime;
        }
    }


    public interface TimTaskUpdateListener {
        void update(int totalTime, int currentTime);
    }
}
