package com.example.javamanythreadsproject.day02;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者: 李纯
 * 时间: 2018/6/5
 * 说明: 公平锁和非公平锁的学习和练习
 */
public class Demo03 {


    public static void main(String[] args) {
        final MyService service = new MyService(true);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };

        Thread[] ts = new Thread[10];
        for(int i = 0; i<10;i++){
            ts[i] = new Thread(r);
        }

        for(int i = 0; i<10;i++){
            ts[i].start();
        }


    }




    static class MyService{
        private ReentrantLock mReentrantLock;

        public MyService(boolean isFair){
            mReentrantLock = new ReentrantLock(isFair);
        }


        public void serviceMethod(){
            try {
                mReentrantLock.lock();
                System.out.println(Thread.currentThread().getName()+"正在执行方法...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mReentrantLock.unlock();
            }
        }
    }
}
