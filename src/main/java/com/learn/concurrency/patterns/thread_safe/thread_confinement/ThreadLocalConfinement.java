package com.learn.concurrency.patterns.thread_safe.thread_confinement;

/**
 * @author zhuwh
 * @date 2018/7/20 15:17
 * @desc
 */
public class ThreadLocalConfinement {

    private static final ThreadLocal<Object> threadLocalObject = new ThreadLocal<Object>() {

        @Override
        protected Object initialValue() {
            return new Object();
        }
    };

    public Object getNowThreadSafeObjectInstance() {
        return threadLocalObject.get();
    }
}
