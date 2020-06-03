package com.learn.concurrency.patterns.resource_pool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuwh
 * @date 2018/7/18 17:23
 * @desc
 */
public class ResourcePool<T> {

    private final static TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private Semaphore semaphore;

    private BlockingQueue<T> resources;

    public ResourcePool(int poolSize, List<T> resourceList){
        this.semaphore = new Semaphore(poolSize,true);
        this.resources = new LinkedBlockingDeque<>(poolSize);
        this.resources.addAll(resourceList);
    }

    public T get() throws InterruptedException{
        return get(Integer.MAX_VALUE);
    }
    public T get(long secondsToTimeout) throws InterruptedException {
        semaphore.acquire();
        try {
            T resource = resources.poll(secondsToTimeout, TIME_UNIT);
            return resource;
        } finally {
            semaphore.release();
        }
    }

    public void release(T resource) throws InterruptedException {
        if (resource != null) {
            resources.put(resource);
            semaphore.release();
        }
    }
}
