package com.learn.concurrency.features.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingIntrinsicLocks {

    private boolean state;

    public synchronized void mySynchronizedmethod(){
        state = !state;

        System.out.println("My state is:"+state);

    }

    public void mySynchronizedBlock(){

        System.out.println("Who owns my lock"+Thread.currentThread().getName());

        synchronized (this){
            state =!state;
            System.out.println("Who owns my lock after state changes: " + Thread.currentThread().getName());
            System.out.println("State is: " + state);
            System.out.println("====");
        }
    }

    public synchronized void reentrancy(){
        System.out.println("Before acquiring again");

        synchronized (this){
            System.out.println("I'm own it!"+Thread.currentThread().getName());
        }
    }

    public static  void main(String []args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        UsingIntrinsicLocks usingIntrinsicLocks = new UsingIntrinsicLocks();
        for(int i=0;i<100;i++){
            executorService.execute(()->usingIntrinsicLocks.mySynchronizedBlock());
        }
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> usingIntrinsicLocks.mySynchronizedBlock());
        }
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> usingIntrinsicLocks.reentrancy());
        }
        executorService.shutdown();
    }
}
