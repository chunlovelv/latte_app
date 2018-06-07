package com.example.javamanythreadsproject.day01;

public class Demo03 {

    public static void main(String[] args) {
        Service s = new ServiceChild();
        A a = new A(s);
        a.start();

        B b = new B(s);
        b.start();
    }

    static class Service{
        synchronized public void printA(){
            try {
                System.out.println("printA开始...");
                boolean isTrue = true;
                while (isTrue){

                }
                System.out.println("printA结束...");
            }catch (Exception e){

            }
        }


        synchronized public void printB(){
            try {
                System.out.println("printB开始...");

                System.out.println("printB结束...");
            }catch (Exception e){

            }
        }
    }

    static class ServiceChild extends Service{
        Object a = new Object();
        public void printA(){
            try {
                synchronized (a){
                    System.out.println("printA开始...");
                    boolean isTrue = true;
                    while (isTrue){

                    }
                    System.out.println("printA结束...");
                }
            }catch (Exception e){

            }
        }

        Object b = new Object();
        public void printB(){
            try {
                synchronized (b){
                    System.out.println("printB开始...");

                    System.out.println("printB结束...");
                }
            }catch (Exception e){

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
            mService.printA();
        }
    }

    static class B extends Thread{
        Service mService;
        public B(Service s){
            mService = s;
        }
        @Override
        public void run() {
            mService.printB();
        }
    }
}
