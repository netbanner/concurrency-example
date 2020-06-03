package com.learn.concurrency.patterns.thread_safe.publishing;

/**
 * @author zhuwh
 * @date 2018/7/20 15:03
 * @desc
 */
public class SafePublishing {
    public static Object object;

    {
        // use static field or a static block to initialize
        // static initialization is safe because it's done automatically locked.
        object = new Object();
    }
}
