package com.example.javamanythreadsproject.day01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者: 李纯
 * 时间: 2018/5/31
 * 说明: 使用原子类进行i++
 */
public class Demo06 {

    private Demo06 demo06;

    public static void main(String[] args) throws InterruptedException {
        AddCountThread s = new AddCountThread();
        for(int i = 0; i<5; i++){
            Thread t1 = new Thread(s);
            t1.start();
        }


    }

    static class AddCountThread extends Thread{
        AtomicInteger count = new AtomicInteger();
        @Override
        public void run() {
            for (int i = 0; i < 100; i++){
                System.out.println(count.incrementAndGet());
            }
        }
    }
}
