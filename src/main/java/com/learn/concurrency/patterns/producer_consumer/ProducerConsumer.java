package com.learn.concurrency.patterns.producer_consumer;

import java.util.UUID;
import java.util.concurrent.*;

public class ProducerConsumer {

    private BlockingQueue<String> data = new LinkedBlockingDeque<>();

    private Callable<Void> consumer = ()->{
        while (true) {
            String dataUnit = data.poll(5, TimeUnit.SECONDS);
            if (dataUnit == null)
                break;
            System.out.println("Consumed " + dataUnit + " from " + Thread.currentThread().getName());
        }

        return  null;
    };

    private Callable<Void> producer = () -> {
        for (int i = 0; i < 90_000; i++) {
            String dataUnit = UUID.randomUUID().toString();
            data.put(dataUnit);
        }
        return null;
    };

    public void run(long forHowLong, TimeUnit unit) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(producer);
        pool.submit(consumer);
        pool.submit(consumer);
        pool.shutdown();
        pool.awaitTermination(forHowLong, unit);
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        try {
            producerConsumer.run(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
