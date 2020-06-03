package com.learn.concurrency.patterns.task_cancel;

/**
 * @author zhuwh
 * @date 2018/7/19 13:50
 * @desc
 */
public class ThreadTaskCancel {

    private Thread thread;

    private Runnable task=()->{
            while (!Thread.currentThread().isInterrupted()){

            }
    };

    public void run(){
        thread = new Thread(task);
        thread.start();
    }

    public void cancel(){
        if(thread!=null){
            thread.isInterrupted();
        }
    }
}
