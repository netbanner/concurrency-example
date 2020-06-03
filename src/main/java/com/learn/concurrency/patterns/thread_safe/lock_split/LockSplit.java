package com.learn.concurrency.patterns.thread_safe.lock_split;

import com.learn.concurrency.patterns.GuardedBy;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhuwh
 * @date 2018/7/19 17:05
 * @desc
 */
public class LockSplit {


    @GuardedBy("lockState")
    private List<Object> hotState;

    @GuardedBy("lockAnotherState")
    private Object anotherSafe;

    @GuardedBy("lockOtherState")
    private Object otherState;

    private Lock lockState = new ReentrantLock();
    private Lock lockOtherStates = new ReentrantLock();

    public List<Object> stateReader(){
        lockState.lock();
        try{
            return  hotState;
        }finally {
            lockState.unlock();
        }
    }

    public void stateWriterMethod(Object param) {
        lockState.lock();
        try {
            this.hotState.add(param);
        } finally {
            lockState.unlock();
        }
    }

    public Object anotherStateReader() {
        lockOtherStates.lock();
        try {
            return anotherSafe;
        } finally {
            lockOtherStates.unlock();
        }
    }

    public void anotherStateWriterMethod(Object param) {
        lockOtherStates.lock();
        try {
            this.anotherSafe = param;
        } finally {
            lockOtherStates.unlock();
        }
    }

    public Object otherStateReader() {
        lockOtherStates.lock();
        try {
            return otherState;
        } finally {
            lockOtherStates.unlock();
        }
    }

    public void otherStateWriterMethod(Object param) {
        lockOtherStates.lock();
        try {
            this.otherState = param;
        } finally {
            lockOtherStates.unlock();
        }
    }
}
