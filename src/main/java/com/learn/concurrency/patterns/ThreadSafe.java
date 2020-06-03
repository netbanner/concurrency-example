package com.learn.concurrency.patterns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuwh
 * @date 2018/7/19 16:12
 * @desc
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadSafe {

}
