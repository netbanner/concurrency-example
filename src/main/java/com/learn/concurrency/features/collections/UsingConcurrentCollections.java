package com.learn.concurrency.features.collections;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

public class UsingConcurrentCollections {

    public static void usingConcurrentHashMap(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("=== ConcurrentHashMap ===");

        Random random = new Random();
        ConcurrentHashMap<UUID,Integer> valuesPerUuid = new ConcurrentHashMap<>();

        valuesPerUuid.putIfAbsent(UUID.randomUUID(),random.nextInt(1000));

        for(int i=0;i<100;i++){
            if(i%6==0){
                executorService.execute(()->{
                    UUID uuid = UUID.randomUUID();
                    Integer value = random.nextInt(10);
                    valuesPerUuid.putIfAbsent(uuid,value);
                });
            }else{
                executorService.execute(()->System.out.println("Printed "+valuesPerUuid.values().toString()));
            }
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(200, TimeUnit.SECONDS);
            Thread.sleep(2000);
            System.out.println("\n\n\n\n");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static  void usingCopyOnWriteArrayList(){

        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("==CopyOnWriteArrayList");
        Random random = new Random();

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        for(int i=0;i<100;i++){
            if(i%8==0){
                //write
                executorService.execute(()->{
                    Integer value = random.nextInt(10);
                    System.err.println("Added" +value);
                    copyOnWriteArrayList.add(value);
                });
            }else{
                // read
                executorService.execute(()->{
                    StringBuilder sb = new StringBuilder();
                    for(Integer vv:copyOnWriteArrayList){
                        sb.append(vv+" ");
                    }
                    System.out.println("Reading " +sb.toString());
                });
            }
        }

        // Finishing
        executorService.shutdown();
        try {
            executorService.awaitTermination(2000, TimeUnit.SECONDS);
            // space for other examples
            Thread.sleep(2000);
            System.out.println("\n\n\n\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void usingBlockingQueue(){
        System.out.println("===BlockingQueue===");

        BlockingQueue<UUID> queue = new LinkedBlockingDeque<>(10);

        System.out.println("Queue will execute for 10s");

        Runnable runnable = ()->{
            while (!Thread.currentThread().isInterrupted()){
                try {
                    UUID uuid = queue.take();
                    System.out.println("Consumed: " + uuid + " by " + Thread.currentThread().getName());

                } catch (InterruptedException e) {
                    // interrupted pattern
                    System.err.println("Consumer Finished");
                }
            }
        };

        Thread consumer1 = new Thread(runnable);
        consumer1.start();
        Thread consumer2 = new Thread(runnable);
        consumer2.start();

        Runnable producer = ()->{
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Random r = new Random();
                    // Delay producer
                    Thread.sleep(r.nextInt(1000));
                    UUID randomUUID = UUID.randomUUID();
                    System.out.println("Produced: " + randomUUID + " by " + Thread.currentThread().getName());
                    queue.put(randomUUID);
                }
            } catch (InterruptedException e) {
                // interrupted pattern
                System.err.println("Producer Finished");
            }
        };

        Thread producer1 = new Thread(producer);
        producer1.start();
        Thread producer2 = new Thread(producer);
        producer2.start();
        Thread producer3 = new Thread(producer);
        producer3.start();

        try {
            // Queue will run for 10secs
            Thread.sleep(10000);
            producer1.interrupt();
            producer2.interrupt();
            producer3.interrupt();
            consumer1.interrupt();
            consumer2.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        usingConcurrentHashMap();
        usingCopyOnWriteArrayList();
        usingBlockingQueue();
    }
}
