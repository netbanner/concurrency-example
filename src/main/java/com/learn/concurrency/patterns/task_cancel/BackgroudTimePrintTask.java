package com.learn.concurrency.patterns.task_cancel;

import java.beans.SimpleBeanInfo;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuwh
 * @date 2018/7/19 13:59
 * @desc
 */
public class BackgroudTimePrintTask {

    private Thread thread;

    private Runnable task=()->{
        while(!Thread.currentThread().isInterrupted()){
            Date date = new Date(System.currentTimeMillis());
            System.out.println(new SimpleDateFormat().format(date));
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
                thread.interrupt();
            }
        }
    };

    public void run() {
        thread = new Thread(task);
        thread.start();
    }

    public void cancel() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public static void main(String[] args) {
        BackgroudTimePrintTask bttt = new BackgroudTimePrintTask();
        bttt.run();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bttt.cancel();
    }
}
