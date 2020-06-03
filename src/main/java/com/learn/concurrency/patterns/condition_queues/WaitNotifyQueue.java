package com.learn.concurrency.patterns.condition_queues;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class WaitNotifyQueue {

    private boolean continueToNotify;

    private BlockingQueue<String> messages;

    public WaitNotifyQueue(List<String> messages){
        this.messages = new LinkedBlockingDeque<>(messages);
        this.continueToNotify = true;
    }

    public synchronized  void stopMessages(){
        continueToNotify = false;
        notifyAll();
    }

    public synchronized  void message() throws InterruptedException{
        while (!continueToNotify)
            wait();
        String message = messages.take();
        System.out.println(message);
    }

    public static  void main(String []args){
        List<String> messages = new LinkedList<>();
        for (int i = 0; i < 130; i++) {
            messages.add(UUID.randomUUID().toString());
        }
        WaitNotifyQueue waitNotifyQueue = new WaitNotifyQueue(messages);

        for(int i=0;i<130;i++){
            messages.add(UUID.randomUUID().toString());
        }
        new Thread(()->{
            try {
                while (true){
                    waitNotifyQueue.message();
                    Thread.sleep(300);
                }
            }catch (Exception e){
                e.printStackTrace();
        }
        }).start();
        Random r = new Random();
        new Thread(()->{
            while (true){
                int val = r.nextInt(100);
                System.out.println(val);
                if(val==99){
                    break;
                }
                try{

                    Thread.sleep(400);
                }catch (Exception e){
                    e.printStackTrace();
                }

                waitNotifyQueue.stopMessages();
            }try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
