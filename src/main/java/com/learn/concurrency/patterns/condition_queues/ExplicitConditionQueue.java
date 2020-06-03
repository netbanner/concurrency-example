package com.learn.concurrency.patterns.condition_queues;

import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExplicitConditionQueue {

    private static  final int LIMIT =8;

    private int messageCount = 0;

    private Lock lock = new ReentrantLock();

    private Condition limitReachedCondition = lock.newCondition();

    private Condition limitUnreachedConditon = lock.newCondition();

    public void stopMessages() throws InterruptedException{
        lock.lock();
        try{
                while (messageCount<LIMIT){
                    limitReachedCondition.await();
                }
                System.err.println("Limit reached. Wait 2s");
            Thread.sleep(2000);
            messageCount = 0;
            limitUnreachedConditon.signalAll();

        }finally {
            lock.unlock();
        }
    }

    public void printMessage(String message) throws InterruptedException {
        lock.lock();
        try{
            while (messageCount==LIMIT){
                limitUnreachedConditon.await();
            }
            System.out.println(message);
            messageCount++;
            limitReachedCondition.signalAll();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ExplicitConditionQueue eqc = new ExplicitConditionQueue();
        // Will run indefinitely
        new Thread(() -> {
            while (true) {
                String uuidMessage = UUID.randomUUID().toString();
                try {
                    eqc.printMessage(uuidMessage);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    eqc.stopMessages();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
