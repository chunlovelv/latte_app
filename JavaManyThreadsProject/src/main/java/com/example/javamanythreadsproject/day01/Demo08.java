package com.example.javamanythreadsproject.day01;

/**
 * 作者: 李纯
 * 时间: 2018/5/31
 * 说明: 生产者和消费者实现线程通信
 */
public class Demo08 {
    public static String VALUE ="";

    public static void main(String[] args) {
        String str = "";
        P p = new P(str);
        ThreadP threadP = new ThreadP(p);
        threadP.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        C c = new C(str);
        ThreadC threadC = new ThreadC(c);
        threadC.start();

    }



    static class P{
        String lock;
        public P(String lock){
            this.lock = lock;
        }

        public void setValue(){
            synchronized (lock){
                try {
                    if(!Demo08.VALUE.equals("")){
                            lock.wait();

                    }
                    String v = System.currentTimeMillis() +"_"+System.nanoTime();
                    System.out.println("set的值是==> " + v);
                    Demo08.VALUE = v;
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class C{
        String lock;

        public C(String lock) {
            this.lock = lock;
        }

        public void getValue(){
            synchronized (lock){
                try {
                    if(Demo08.VALUE.equals("")){
                            lock.wait();
                    }
                    System.out.println("get的值是: "+Demo08.VALUE);
                    Demo08.VALUE = "";
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    static class ThreadP extends Thread{
        P p;
        public ThreadP(P p){
            this.p = p;
        }
        @Override
        public void run() {
            while (true){
                p.setValue();
            }
        }
    }

    static class ThreadC extends Thread{
        C c;
        public ThreadC(C c){
            this.c = c;
        }
        @Override
        public void run() {
            while (true){
                c.getValue();
            }
        }
    }
}
