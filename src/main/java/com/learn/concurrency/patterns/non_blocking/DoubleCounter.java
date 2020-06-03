package com.learn.concurrency.patterns.non_blocking;

import java.util.concurrent.atomic.AtomicInteger;

public class DoubleCounter {

    private AtomicInteger value = new AtomicInteger(0);

    public void increate(){

        int updateValue ;
        int old;

        do{
            old = value.get();
            updateValue = old +2;
        }while (!value.compareAndSet(old,updateValue));
    }

    public int getValue() {
        return value.get();
    }
}
