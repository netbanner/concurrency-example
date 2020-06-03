package com.learn.concurrency.patterns.task_convergence;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhuwh
 * @date 2018/7/19 14:34
 * @desc
 */
public class TaskConvergence {

    private static final int BOUND =150_000;

    private static final int ITERS = 400_000;

    private static final int CORES = Runtime.getRuntime().availableProcessors();

    private CyclicBarrier barrier;

    private List<Long> synchroizedLinkedList;

    private ExecutorService executor;

    private Runnable run = ()->{
        Random random = new Random();
        List<Long> results = new LinkedList<>();
        for(int i=0;i<ITERS;i++){
            Long next = (long) random.nextInt(BOUND);
            results.add(next);
        }
        try{
            persist(results);
            barrier.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    private Runnable onComplete = ()->{
        System.out.println("=== Random Number Results ===");
        System.out.println("CPU Cores: " + CORES);
        System.out.println("Random Bound: " + BOUND);
        System.out.println("Iterations per Core: " + ITERS);
        System.out.println("Total Iterations: " + ITERS * CORES);
        System.out.println("Size: " + synchroizedLinkedList.size());
        System.out.println("Sum " + synchroizedLinkedList.stream().mapToLong(Long::longValue).sum());
    };

    public TaskConvergence(){
        barrier = new CyclicBarrier(CORES,onComplete);
        synchroizedLinkedList = Collections.synchronizedList(new LinkedList<>());
        executor = Executors.newFixedThreadPool(CORES);
    }

    public void run(){
        for(int i=0;i<CORES;i++){
            executor.execute(run);
        }
    }

    private void persist(List<Long> randomNumbers){
        synchroizedLinkedList.addAll(randomNumbers);
    }

    public static  void main(String []args){
        new TaskConvergence().run();
    }
}
