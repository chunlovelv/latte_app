package com.example.javamanythreadsproject.real_exc.wait_notify_insert_test;

/**
 * 作者: 李纯
 * 时间: 2018/6/4
 * 说明: 数据库备份
 */
public class DbTools {
    private volatile boolean prevIsA = true;

    /**
     * 备份数据A
     */
    synchronized public void backupA() {
        try {
            while (prevIsA) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("A A A A A");
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }

    }

    /**
     * 备份数据B
     */
    synchronized public void backupB() {
        try {
            while (!prevIsA) {
                wait();
            }

            for (int i = 0; i < 5; i++) {
                System.out.println("B B B B B");
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
