package com.learn.concurrency.patterns.controlled_initialization;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControlledInitialization {

    static  class Resources1{

    }

    static class Resources2{

    }

    static  class  Resources3{

    }

    private Resources1 resource1;
    private Resources2 resource2;
    private Resources3 resource3;


    private CountDownLatch latch = new CountDownLatch(3);

    private Runnable initResource1 = ()->{
        try{
            Thread.sleep(4000);
            resource1 = new Resources1();
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    };

    private Runnable initResource2 = ()->{
        try{
            Thread.sleep(4000);
            resource2 = new Resources2();
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    };

    private  Runnable initResource3 = ()->{
        try{
            Thread.sleep(4000);
            resource3 = new Resources3();
            latch.countDown();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    };

    public ControlledInitialization(){
        initialize();
        try{
            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        doTask();
    }

    private void doTask() {
        System.out.println("=== Resources initialized ===");
        System.out.println("Resource 1 instance " + resource1);
        System.out.println("Resource 2 instance " + resource2);
        System.out.println("Resource 3 instance " + resource3);

    }

    private void initialize() {
        System.out.println("=== Initializing Resources ===");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(initResource1);
        executor.execute(initResource2);
        executor.execute(initResource3);
        executor.shutdown();

    }

    public static void main(String[] args) {
        new ControlledInitialization();
    }
}
