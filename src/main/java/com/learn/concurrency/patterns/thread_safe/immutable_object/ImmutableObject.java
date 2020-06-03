package com.learn.concurrency.patterns.thread_safe.immutable_object;

/**
 * @author zhuwh
 * @date 2018/7/19 16:47
 * @desc
 */
public class ImmutableObject {

    private volatile  MyImmutableObject safeReference = new MyImmutableObject(0,"",false);

    public void updateField(int id,String newValue,boolean newAnotherValue){
        this.safeReference = safeReference;
    }

    public void printMyImmutableObject() {
        System.out.println(safeReference);
    }

    public final static class MyImmutableObject {
        private final int id;
        private final String aValue;
        private final boolean anotherValue;

        public MyImmutableObject(int id, String aValue, boolean anotherValue) {
            super();
            this.id = id;
            this.aValue = aValue;
            this.anotherValue = anotherValue;
        }

        public int getId() {
            return id;
        }

        public String getaValue() {
            return aValue;
        }

        public boolean getAnotherValue() {
            return anotherValue;
        }

        @Override
        public String toString() {
            return "MyImmutableObject [id=" + id + ", aValue=" + aValue + ", anotherValue=" + anotherValue + "]";
        }

    }

}
