package com.learn.concurrency.patterns.fixed_lock_ordering;

/**
 * @author zhuwh
 * @date 2018/6/19 17:31
 * @desc
 */
public class FixedLockOrdering {

    static class LockableObject{
        private int id;

        private String anotherValue;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAnotherValue() {
            return anotherValue;
        }

        public void setAnotherValue(String anotherValue) {
            this.anotherValue = anotherValue;
        }
    }

    public void doSomeOperation(LockableObject obj1,LockableObject obj2){
        int obj1Id = obj1.getId();
        int obj2Id = obj2.getId();
        if(obj1Id<obj2Id){
            synchronized (obj1){
                synchronized (obj2){
                    System.out.println("obj1Id="+obj1Id);
                }
            }
        }else{
            synchronized (obj2){
                synchronized (obj1){
                    System.out.println("obj2Id="+obj2Id);
                }
            }
        }
    }
}
