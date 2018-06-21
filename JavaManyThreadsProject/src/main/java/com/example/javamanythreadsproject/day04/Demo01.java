package com.example.javamanythreadsproject.day04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：lichun
 * 时间：2018/6/8
 * 描述：线程池的学习和用法
 */
public class Demo01 {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
