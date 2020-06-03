package com.learn.concurrency.features.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class UsingExplicitReentrantLocks {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private boolean state;

    public void lockMyHealth(){

        reentrantLock.lock();
        try {
            System.out.println("Changing stated in a serialized way");
            state = !state;
            System.out.println("Changed: " + state);
        } finally {
            reentrantLock.unlock();
        }
    }
    /**
     * Try lock - Timed and polled lock acquisition
     *
     * @throws InterruptedException
     */
    public void lockMyHearthWithTiming() throws InterruptedException {
        // Tries to acquire lock in the specified timeout
        if (!reentrantLock.tryLock(1l, TimeUnit.SECONDS)) {
            System.err.println("Failed to acquire the lock - it's already held.");
        } else {
            try {
                System.out.println("Simulating a blocking computation - forcing tryLock() to fail");
                Thread.sleep(3000);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String []args){
        ExecutorService executor = Executors.newCachedThreadPool();
        UsingExplicitReentrantLocks uel = new UsingExplicitReentrantLocks();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> uel.lockMyHealth());
        }

        for (int i = 0; i < 40; i++) {
            executor.execute(() -> {
                try {
                    uel.lockMyHearthWithTiming();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
