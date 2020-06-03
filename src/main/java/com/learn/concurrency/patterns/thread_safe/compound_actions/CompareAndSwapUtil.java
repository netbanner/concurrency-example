package com.learn.concurrency.patterns.thread_safe.compound_actions;

/**
 * @author zhuwh
 * @date 2018/7/19 16:26
 * @desc
 */
public class CompareAndSwapUtil {

    private int value;

    public synchronized int getValue(){
        return  value;
    }

    public synchronized int compareAndSwap(int expected,int newValue){
        int old = value;
        if(old==expected){
            value = newValue;
        }
        return old;
    }

}
