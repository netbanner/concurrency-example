package com.learn.concurrency.features.collections;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuwh
 * @date 2018/7/18 16:18
 * @desc
 */
public class UsingSynchronizedCollections {

    public static void insertIfAbsent(Vector<Long> list, Long value) {
        synchronized (list) {
            boolean contains = list.contains(value);
            if (!contains) {
                list.add(value);
                System.out.println("add value {} to list " + value);
            }
        }

    }


    public static void insertUnSafeIfAbsent(Vector<Long> list, Long value) {
        boolean contains = list.contains(value);
        if (!contains) {
            list.add(value);
            System.out.println("add value {} to list " + value);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        Vector<Long> vector = new Vector<>();
        Runnable insertIfAbsent = () -> {
            long millis = System.currentTimeMillis() / 1000;
            insertIfAbsent(vector, millis);
        };

        for (int i = 0; i < 10001; i++) {
            service.execute(insertIfAbsent);
        }
        service.shutdown();
        service.awaitTermination(4000, TimeUnit.SECONDS);
    }
}



