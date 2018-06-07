package com.example.javamanythreadsproject.day02;

import com.example.javamanythreadsproject.utils.DataUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者: 李纯
 * 时间: 2018/6/5
 * 说明: TimerTask的用法和练习
 */
public class Demo05 {

    public static void main(String[] args) {
        MyService myService = new MyService();
        myService.run();
    }


    static class MyService {
        private Timer mTimer = new Timer();
        private MyTimerTask mTimerTask = new MyTimerTask();

        static class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                System.out.println("运行了，运行时间是：" + DataUtil.getCurrentTime());
            }
        }


        public void run() {
            //5秒之后开始执行，每1秒循环执行一次
            mTimer.schedule(mTimerTask, 5000, 1000);
        }

    }


}
