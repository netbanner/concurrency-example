package com.learn.concurrency.patterns.thread_safe.thread_confinement;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuwh
 * @date 2018/7/20 15:27
 * @desc
 */
public class ThreadSafeDateFormat {

    private static final ThreadLocal<SimpleDateFormat> threadLocalDateFormat = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        }
    };

    public String format(Date date){
        return threadLocalDateFormat.get().format(date);
    }
}
