package com.learn.concurrency.features.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class UsingCompletableFuture {

    public static void main(String []args) throws  Exception{

        Random random = new Random();

        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> randomNum = CompletableFuture.supplyAsync(()->random.nextInt(140),executorService);

        String strNum = randomNum.thenApplyAsync(n->Integer.toString(n),executorService).get();

        System.out.print("Executed +"+strNum);


        CompletableFuture<Integer> anotherNum = CompletableFuture.supplyAsync(() -> random.nextInt(140), executorService);

        // accept both and do something
        randomNum.thenAcceptBoth(anotherNum, (num1, num2) -> {
            System.out.println("Num1 is: " + num1);
            System.out.println("Num2 is: " + num2);
        });

        // combine both into a new type/value
        CompletableFuture<Integer> mappedAndCombined = randomNum.thenCombine(anotherNum, (num1, num2) -> num1 + num2);

        // retrieving value
        Integer value = mappedAndCombined.get();
        System.out.println("Sum " + value);

        // Indefined time task
        Supplier<Double> ind = () -> {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return random.nextDouble();
        };

        // Run after executed
        CompletableFuture<Double> f1 = CompletableFuture.supplyAsync(ind);
        CompletableFuture<Double> f2 = CompletableFuture.supplyAsync(ind);
        CompletableFuture<Double> f3 = CompletableFuture.supplyAsync(ind);
        CompletableFuture<Double> f4 = CompletableFuture.supplyAsync(ind);
        CompletableFuture.anyOf(f1, f2, f3, f4).thenRun(() -> System.out.println("Completed"));

        Supplier<String> getVal = ()->{

            return  "first";
        };

        Supplier<String> getVal2 = ()->{

            return  "Second";
        };

        CompletableFuture.supplyAsync(getVal)
                .acceptEitherAsync(CompletableFuture.supplyAsync(getVal2,executorService),(firstToBeReady)->System.out.println(firstToBeReady),executorService);

        executorService.shutdown();
        executorService.awaitTermination(3000, TimeUnit.SECONDS);

    }
}
