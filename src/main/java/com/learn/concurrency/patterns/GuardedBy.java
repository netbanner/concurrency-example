package com.learn.concurrency.patterns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuwh
 * @date 2018/7/19 16:16
 * @desc
 */
@Target(value = {ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface GuardedBy {
     String value();
}
