package com.learn.concurrency.features.locks;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhuwh
 * @date 2018/7/18 16:59
 * @desc
 */
public class UsingExplicReadWriteLocks {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String myContent = "A long default content ...";

    public String showContent(){
        ReentrantReadWriteLock.ReadLock  readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            System.out.println("Reading state while holding a lock.");
            return myContent;
        }finally {
            readLock.unlock();
        }
    }

    public void writeContent(String string){
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            System.err.println("Writing " + string);
            myContent = new StringBuffer(myContent).append(string).toString();

        }finally {
            writeLock.unlock();
        }
    }


    public static  void main(String []args){
        ExecutorService service = Executors.newCachedThreadPool();
        UsingExplicReadWriteLocks uerwl = new UsingExplicReadWriteLocks();

        for(int i=0;i<100;i++){
            service.submit(()->{
                try {
                    // Delay readers to start
                    Thread.sleep(new Random().nextInt(10) * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(uerwl.showContent());
            });
        }

        for (int i = 0; i < 5; i++) {
            service.execute(() -> uerwl.writeContent(UUID.randomUUID().toString()));
        }
        service.shutdown();

    }

}
