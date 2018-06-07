package com.example.javamanythreadsproject.day01;

public class Demo02 {
    public static void main(String[] args) {
        Service s = new Service();
        A a = new A(s);
        a.setName("AAA");
        a.start();

        B b = new B(s);
        b.setName("BBB");
        b.start();

    }

    static class Service{
        public void printStr(String string){
            try {
                synchronized (string){
                    while (true){
                        System.out.println("currentThreadName==> "+Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
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
            mService.printStr("A");
        }
    }

    static class B extends Thread{
        Service mService;
        public B(Service s){
            mService = s;
        }
        @Override
        public void run() {
            mService.printStr("A");
        }
    }
}
