package com.example.javamanythreadsproject.day02;

import java.time.chrono.ThaiBuddhistChronology;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者: 李纯
 * 时间: 2018/6/5
 * 说明: ReentrantLock类的使用和练习(使用多个Condition实现线程的等待与通知)
 */
public class Demo02 {

    public static void main(String[] args) throws InterruptedException {
        MoreConditionService moreConditionService = new MoreConditionService();

        ThreadTest t1 = new ThreadTest(moreConditionService);
        t1.setName("ThreadA");
        t1.start();
        ThreadTest t2 = new ThreadTest(moreConditionService);
        t2.setName("ThreadB");
        t2.start();
        ThreadTest t3 = new ThreadTest(moreConditionService);
        t3.setName("ThreadC");
        t3.start();

    }


    static class MoreConditionService{
        private ReentrantLock mReentrantLock = new ReentrantLock();

        private Condition mCondition1 = mReentrantLock.newCondition();

        private Condition mCondition2 = mReentrantLock.newCondition();

        private LinkedHashMap<Thread, Condition> containers = new LinkedHashMap<>();

        public void await(){
            Thread thread = Thread.currentThread();
            Condition condition = null;
            if(containers.containsKey(thread)){
                condition = containers.get(thread);
            }else{
                condition = mReentrantLock.newCondition();
                containers.put(thread, condition);
            }

            try {
                mReentrantLock.lock();
                System.out.println(Thread.currentThread().getName()+"开始等待...");
                condition.await();
                System.out.println(Thread.currentThread().getName()+"结束等待，再次执行...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mReentrantLock.unlock();
            }
        }


        public void asignl(Thread thread){
            Condition condition = null;
            if(containers.containsKey(thread)){
                System.out.println("************************");
                condition = containers.get(thread);

                try {
                    mReentrantLock.lock();
                    condition.signalAll();
                    System.out.println("通知"+Thread.currentThread().getName()+"结束等待");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mReentrantLock.unlock();
                }
            }
        }


        public void waitA(){
            try {
                mReentrantLock.lock();
                System.out.println("线程A开始等待...");
                mCondition1.await();
                System.out.println("线程A结束等待，再次执行...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mReentrantLock.unlock();
            }
        }

        public void waitB(){
            try {
                mReentrantLock.lock();
                System.out.println("线程B开始等待...");
                mCondition2.await();
                System.out.println("线程B结束等待，再次执行...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mReentrantLock.unlock();
            }
        }


    }


    static class ThreadTest extends Thread{
        MoreConditionService mMoreConditionService;
        public ThreadTest(MoreConditionService moreConditionService){
            mMoreConditionService = moreConditionService;
        }
        @Override
        public void run() {
            mMoreConditionService.await();
        }


        public void asign(){
            LinkedHashMap<Thread, Condition> containers = mMoreConditionService.containers;
            for (Thread t : containers.keySet()){
                mMoreConditionService.asignl(t);
                break;
            }
        }
    }

}




