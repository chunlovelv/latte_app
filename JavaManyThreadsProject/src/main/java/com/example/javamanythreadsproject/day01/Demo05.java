package com.example.javamanythreadsproject.day01;

/**
 * volitile的非原子性
 */
public class Demo05 {

    public static void main(String[] args) {
//        Service s = new Ser vice();
//        A a = new A(s);
//        a.start();
//        B t2 = new B(s);
//        t2.start();
        RunThread thread = new RunThread();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread.setRuning(false);
    }



    static class RunThread extends Thread{
        volatile private boolean isRuning = true;

        public void setRuning(boolean runing) {
            isRuning = runing;
        }

        @Override
        public void run() {
            System.out.println("线程开始执行");
            while (isRuning){

            }
            System.out.println("线程结束执行");
        }
    }


    static class Service {
        private int i ;
        public void print() {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("print==> "+i);
            }

        }

        public void add(){
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }

        }
    }

    static class A extends Thread{
        Service mService;
        public A(Service s){
            mService = s;
        }
        @Override
        public void run() {
            mService.print();
        }
    }

    static class B extends Thread{
        Service mService;
        public B(Service s){
            mService = s;
        }
        @Override
        public void run() {
            mService.add();
        }
    }
}
