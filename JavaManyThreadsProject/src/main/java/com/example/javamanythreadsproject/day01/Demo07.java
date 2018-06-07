package com.example.javamanythreadsproject.day01;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 李纯
 * 时间: 2018/5/31
 * 说明: 通过notify和wait来实现多线程通信
 */
public class Demo07 {

    public static void main(String[] args) throws InterruptedException {
        MyList myList = new MyList();
        Thread1 thread1 = new Thread1(myList);
        thread1.start();
        Thread.sleep(1000);
        Thread2 thread2 = new Thread2(myList);
        thread2.start();

    }



    static class MyList{
        private List<String> mList = new ArrayList<>();

        public void add(){
            mList.add("a");
        }

        public int size(){
           return mList.size();
        }
    }


    static class Thread1 extends Thread{
        MyList mObject;
        public Thread1(MyList obj){
            mObject = obj;
        }
        @Override
        public void run() {
            synchronized (mObject){
                if(mObject.size() != 5){
                    System.out.println("Thread1开始等待，释放掉对mObject的锁....");
                    try {
                        mObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread1结束等待....");

                }
            }
        }
    }


    static class Thread2 extends Thread{

        MyList mObject;
        public Thread2(MyList obj){
            mObject = obj;
        }

        @Override
        public void run() {
            try {
                synchronized (mObject){
                    System.out.println("Thread2获取到mObject的对象锁...");
                    for(int i  =0 ;i < 10;i++){
                        mObject.add();
                        if(mObject.size() == 5){
                            mObject.notify();
                            System.out.println("Thread2发送解锁通知...");
                        }

                        Thread.sleep(1000);

                        System.out.println("正在添加第"+i+"个数据");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
