package com.learn.concurrency.patterns.resource_pool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhuwh
 * @date 2018/7/19 13:33
 * @desc
 */
public class ResourcePoolUsage {

    public static void  main(String []args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        ResourcePool<Integer> resourcePool = new ResourcePool<>(15, Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
        Random random = new Random();
        for(int i=0;i<30;i++){
            executorService.execute(()->{
                try{
                    Integer value = resourcePool.get(60);
                    System.out.println("Value take:"+value);
                    Thread.sleep(random.nextInt(5000));
                    resourcePool.release(value);
                    System.out.println("Value release"+value);
                }catch (InterruptedException e){
                    e.printStackTrace();;
                }
            });
        }
        executorService.shutdown();
    }
}
