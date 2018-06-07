package com.example.javamanythreadsproject.day01;

/**
 * 作者: 李纯
 * 时间: 2018/6/4
 * 说明: ThreadLocal的使用练习
 */
public class Demo11 {
    public static final ThreadLocal THREAD_LOCAL = new ThreadLocal();


    public static void main(String[] args) {
//        Object o = THREAD_LOCAL.get();
//        if(o == null){
//            System.out.println("没有值...");
//            THREAD_LOCAL.set("hahhaha");
//        }
        THREAD_LOCAL.set("main Datas");
        THREAD_LOCAL.set("main Datas222222222");

        new MyThread().start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(THREAD_LOCAL.get());
    }


    static class MyThread extends Thread{
        @Override
        public void run() {
            THREAD_LOCAL.set("MyThread datas");
        }
    }

//    public static void main(String[] args){
//        System.out.println("hello,world!!!");
//    }



}
