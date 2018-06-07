package com.example.javamanythreadsproject.day01;

/**
 * 多线程的死锁实现
 */
public class Demo04 {

    public static void main(String[] args) throws InterruptedException {
        Service s = new Service();
        s.setName("a");
        Thread t1 = new Thread(s);
        t1.start();
        Thread.sleep(100);
        s.setName("b");
        Thread t2 = new Thread(s);
        t2.start();
    }


    static class Service implements Runnable {
        String mName;
        Object lock1 = new Object();
        Object lock2 = new Object();

        public Service() {
        }

        public void setName(String name) {
            mName = name;
        }

        @Override
        public void run() {
            if ("a".equals(mName)) {
                synchronized (lock1) {
                    try {
                        System.out.println("usernam==> " + mName);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2){
                        System.out.println("锁住了对象lock2");
                    }
                }
            }

            if ("b".equals(mName)) {
                synchronized (lock2) {
                    try {
                        System.out.println("usernam==> " + mName);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println("锁住了对象lock1");
                    }
                }
            }

        }
    }
}
