package com.learn.concurrency.patterns.thread_safe.compound_actions;

import com.learn.concurrency.patterns.GuardedBy;

/**
 * @author zhuwh
 * @date 2018/7/19 16:14
 * @desc
 */
public class AtomicCompoundActions {

    @GuardedBy("this")
    private Object value;

    public synchronized  void checkThenAct(){
        if(value!=null){

        }
    }
    public void dependentAction(){

    }

    public synchronized void getValue(){}

}
