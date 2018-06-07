package com.example.javamanythreadsproject.real_exc.wait_notify_insert_test;

/**
 * 作者: 李纯
 * 时间: 2018/6/4
 * 说明: 等待通知之交叉备份
 */
public class WaitNotifyInsertTest {

    public static void main(String[] args) {
        DbTools dbTools = new DbTools();

        for (int i = 0; i < 20; i++) {
            ThreadBackupDataA threadBackupDataA = new ThreadBackupDataA(dbTools);

            ThreadBackupDataB threadBackupDataB = new ThreadBackupDataB(dbTools);

            threadBackupDataA.start();
            threadBackupDataB.start();
        }

    }


    static class ThreadBackupDataA extends Thread {
        private final DbTools mDbTools;

        public ThreadBackupDataA(DbTools dbTools) {
            this.mDbTools = dbTools;
        }

        @Override
        public void run() {
            mDbTools.backupA();
        }
    }


    static class ThreadBackupDataB extends Thread {
        private final DbTools mDbTools;

        public ThreadBackupDataB(DbTools dbTools) {
            this.mDbTools = dbTools;
        }

        @Override
        public void run() {
            mDbTools.backupB();
        }
    }
}
