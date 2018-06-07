package com.example.javamanythreadsproject.day01;

/**
 * 作者: 李纯
 * 时间: 2018/6/4
 * 说明: 方法join的使用
 */
public class Demo10 {


    public static void main(String[] args) {
        JoinThread joinThread = new JoinThread();
        joinThread.start();
        try {
            synchronized (joinThread) {
                while (joinThread.isAlive()){
                    System.out.println(7777777);
                    joinThread.wait();
                }
            }
//            joinThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("joinThread线程执行完毕之后，我再执行...");
    }


    static class JoinThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" 哈哈哈");
        }





    }
}
