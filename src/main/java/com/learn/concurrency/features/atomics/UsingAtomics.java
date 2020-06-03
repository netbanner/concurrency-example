package com.learn.concurrency.features.atomics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UsingAtomics {

    static class  AtomicCounter {

        private AtomicInteger atomicInteger = new AtomicInteger();

        public void increment() {
            atomicInteger.incrementAndGet();
        }

        public void decrement() {
            atomicInteger.decrementAndGet();
        }

        public int get(){
            return  atomicInteger.get();
    }

        public static void main(String[] args)  throws InterruptedException {

            AtomicCounter counter = new AtomicCounter();
            ExecutorService executorService = Executors.newCachedThreadPool();
            for(int i=0;i<10000;i++){
                executorService.execute(()->counter.increment());
            }
            executorService.shutdown();
            executorService.awaitTermination(4000, TimeUnit.SECONDS);

            System.out.println("Result shound be 10000: Actual result is: " + counter.get());
        }
    }
}
