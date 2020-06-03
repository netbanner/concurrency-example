package com.learn.concurrency.features.future;

import java.util.Random;
import java.util.concurrent.*;

public class UsingFutureTasks {

    public static void main(String []args){

        int i=1;



            Callable<Integer> callable = () -> {
                int random = new Random().nextInt(10) * 100;
                System.out.println("Preparing to execute");
                Thread.sleep(random);
                System.out.println("Executed - " + random);
                return random;
            };
            i++;

        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(futureTask) ;

        try{
            Integer value = futureTask.get(2, TimeUnit.SECONDS);

            System.out.println("Value is ="+value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
