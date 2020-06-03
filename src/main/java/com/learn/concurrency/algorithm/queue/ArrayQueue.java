package com.learn.concurrency.algorithm.queue;

/**
 * @author zhuwh
 * @date 2019/9/20 9:55
 * @desc
 */
//用数组组织队列
public class ArrayQueue {


    private String []items;

    private int n =0;

    private int head =0;
    private int tail =0;

    public ArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    public boolean enqueue(String item){
        if(tail==n) return false;

        items[tail] = item;
        ++tail;
        return true;
    }

    public String dequeue(){
        if(head==tail) return null;
        String ret = items[head];
        ++head;
        return ret;
    }

    public void printAll(){
        for(int i = head;i<tail;i++){
            System.out.println(items[i]+" ");
        }
        System.out.println();
    }
}
