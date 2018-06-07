package com.example.javamanythreadsproject.day02;

import com.example.javamanythreadsproject.utils.DataUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者: 李纯
 * 时间: 2018/6/5
 * 说明: ReentrantLock类的使用和练习(使用单个Condition实现线程的等待与通知)
 */
public class demo01 {


    public static void main(String[] args) throws InterruptedException {
        LockService lockService = new LockService();
        TestThread tt = new TestThread(lockService);

        tt.start();

        Thread.sleep(2000);

        lockService.asingle();
    }



    static class LockService{
        private ReentrantLock mReentrantLock = new ReentrantLock();
        private Condition mCondition = mReentrantLock.newCondition();

        public void await(){
            try {
                mReentrantLock.lock();
                System.out.println(DataUtil.getCurrentTime() + " 开始等待....");
                mCondition.await();

                System.out.println(DataUtil.getCurrentTime() + " 等待结束，我要做我自己的事情了....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mReentrantLock.unlock();
            }
        }


        public void asingle(){
            try {
                mReentrantLock.lock();
                System.out.println(DataUtil.getCurrentTime()+" 通知线程可以不用等待了...");
                mCondition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mReentrantLock.unlock();
            }
        }
    }


    static class TestThread extends Thread{
        LockService mLockService;

        public TestThread(LockService lockService){
            mLockService = lockService;
        }

        @Override
        public void run() {
            mLockService.await();
        }
    }


}
