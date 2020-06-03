package com.learn.concurrency.patterns.thread_safe.initialization;

/**
 * @author zhuwh
 * @date 2018/7/19 16:51
 * @desc
 */
public class SafeInitializationHolder {

    private static class ResourceHolder{
        public static Object resource = new Object();
    }

    public static  Object getResource(){
        return ResourceHolder.resource;
    }
}
