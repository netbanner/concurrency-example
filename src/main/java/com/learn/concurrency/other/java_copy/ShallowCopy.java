package com.learn.concurrency.other.java_copy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuwh
 * @date 2018/7/20 11:21
 * @desc
 */
public class ShallowCopy {

    private String name;

    private int age;

    public void  hashMapCopy(){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> copyMap = new HashMap<>(map);
    }

    /**
     * 浅拷贝,且必须要实现Cloneable接口
     * *
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


}
