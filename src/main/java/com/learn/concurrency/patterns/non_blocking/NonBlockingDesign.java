package com.learn.concurrency.patterns.non_blocking;

import java.util.concurrent.atomic.AtomicReference;

public class NonBlockingDesign {

    private AtomicReference<Object> value = new AtomicReference<>(new Object());

    public void modifyValue() {
        Object updatedValue;
        Object old;
        do {
            old = value.get(); // get value
            updatedValue = new Object(); // new updated value
        } while (!value.compareAndSet(old, updatedValue));
		/*
		 * if the expected value (old) differs from what is actually present in the
		 * structure, this means that in the mean time some thread changed it. The loop
		 * will run until it reaches an acceptable state.
		 */
    }

    public Object getValue() {
        return value.get();
    }

    /*
     * Modified value...
     */
    public Object newObject() {
        return new Object();
    }
}
