package com.learn.concurrency.features.synchronizers;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingBarriers {

    public static void main(String[] args){

        ExecutorService executorService  = Executors.newCachedThreadPool();

        Runnable barrierAction = ()->System.out.println("Well done!");

        CyclicBarrier barrier =  new CyclicBarrier(10,barrierAction);

        Runnable task = ()->{
            try{
                    System.out.println("Doing task for "+Thread.currentThread().getName());
                    Thread.sleep(new Random().nextInt(10)*100);
                    System.out.println("Done for "+ Thread.currentThread().getName());
                    barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 10; i++) {
            executorService.execute(task);
        }
        executorService.shutdown();

    }
}
