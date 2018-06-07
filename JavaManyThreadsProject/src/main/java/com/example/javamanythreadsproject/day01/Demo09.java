package com.example.javamanythreadsproject.day01;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedWriter;

/**
 * 作者: 李纯
 * 时间: 2018/6/4
 * 说明: 通过管道进行线程通信
 */
public class Demo09 {

    public static void main(String[] args) {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream();

        try {
            pipedInputStream.connect(pipedOutputStream);
            Service service = new Service();
            ReadThread readThread = new ReadThread(service, pipedInputStream);
            readThread.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WriteThread writeThread = new WriteThread(service, pipedOutputStream);
            writeThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class Service {
        public void readData(PipedInputStream pipedInputStream) {
            if(pipedInputStream == null){
                throw new NullPointerException("param pipedInputStream is null");
            }
            byte[] buff = new byte[20];
            try {
                int readLen = pipedInputStream.read(buff);
                while (readLen != -1) {
                    String str = new String(buff, 0, readLen);
                    System.out.println("readResult==> " + str);
                    readLen = pipedInputStream.read(buff);
                }
                System.out.println("");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pipedInputStream != null)
                        pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        public void writeData(PipedOutputStream pipedOutputStream){
            if(pipedOutputStream == null){
                return;
            }

            try {
                for(int i = 0 ;i<100; i++){
                    String data = i+"";
                    pipedOutputStream.write(data.getBytes());
                    System.out.println("outdata==> " +data);
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





    static class ReadThread extends Thread{
        Service mService;
        PipedInputStream mPipedInputStream;
        public ReadThread(Service service, PipedInputStream pipedInputStream){
            mService = service;
            mPipedInputStream = pipedInputStream;
        }

        @Override
        public void run() {
            mService.readData(mPipedInputStream);
        }
    }

    static class WriteThread extends Thread{
        Service mService;
        PipedOutputStream mPipedOutputStream;
        public WriteThread(Service service, PipedOutputStream pipedOutputStream){
            mService = service;
            mPipedOutputStream = pipedOutputStream;
        }

        @Override
        public void run() {
            mService.writeData(mPipedOutputStream);
        }
    }
}
