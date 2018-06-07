package com.example.javamanythreadsproject.day02;

import com.example.javamanythreadsproject.utils.DataUtil;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 作者: 李纯
 * 时间: 2018/6/5
 * 说明: ReentrantReadWriteLock(读写锁的练习和使用)
 */
public class Demo04 {

    public static void main(String[] args) {
        final MyService service = new MyService();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                service.read();
                service.write();
            }
        };

        Thread[] threads = new Thread[10];

        for(int i = 0; i<10; i++){
            threads[i] = new Thread(runnable);
        }

        for(int i = 0; i<10; i++){
            threads[i].start();
        }
    }


    static class MyService{
        ReentrantReadWriteLock mReentrantReadWriteLock = new ReentrantReadWriteLock();

        public void read(){
            try {
                mReentrantReadWriteLock.readLock().lock();
                System.out.println(Thread.currentThread().getName()+"正在读取数据，"+ DataUtil.getCurrentTime());
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mReentrantReadWriteLock.readLock().unlock();
            }
        }

        public void write(){
            try {
                mReentrantReadWriteLock.writeLock().lock();
                System.out.println(Thread.currentThread().getName()+"正在写入数据，"+ DataUtil.getCurrentTime());
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mReentrantReadWriteLock.writeLock().unlock();
            }
        }



    }

}
