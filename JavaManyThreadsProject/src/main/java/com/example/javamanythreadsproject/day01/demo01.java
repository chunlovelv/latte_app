package com.example.javamanythreadsproject.day01;

public class demo01 {

    public static void main(String[] args) {
        Task t = new Task();
//        T1 t1 = new T1(t);
//        T2 t2 = new T2(t);
//        t1.start();
//        t2.start();

        T3 t3= new T3(t);
        T4 t4 = new T4(t);
        t3.start();
        t4.start();
    }

    static class Task {

        public void doSomething(String tName) {
            for (int i = 0; i < 100; i++) {
                System.out.println(tName+"非同步打印" + i);
            }

            synchronized (Task.class) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(tName+"同步打印" + i);
                }
            }
        }


        public void doSomethingA(String tName) {
            synchronized (Task.class) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(tName+"doSomethingA同步打印" + i);
                }
            }
        }

        public void doSomethingB(String tName) {
            synchronized (Task.class) {
                for (int i = 0; i < 100; i++) {
                    System.out.println(tName+"doSomethingB同步打印" + i);
                }
            }
        }






    }


    static class T1 extends Thread {
        Task t;

        public T1(Task task) {
            t = task;
        }

        @Override
        public void run() {
            t.doSomething("t1");
        }
    }

    static class T2 extends Thread {
        Task t;

        public T2(Task task) {
            t = task;
        }

        @Override
        public void run() {
            t.doSomething("t2");
        }
    }



    static class T3 extends Thread {
        Task t;

        public T3(Task task) {
            t = task;
        }

        @Override
        public void run() {
            t.doSomethingA("t3");
        }
    }

    static class T4 extends Thread {
        Task t;

        public T4(Task task) {
            t = task;
        }

        @Override
        public void run() {
            t.doSomethingB("t4");
        }
    }


}


