package com.learn.concurrency.other.java_copy;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * @author zhuwh
 * @date 2018/7/20 11:23
 * @desc
 */
public class DeepCopy {


    /**
     *
     * 过继承Serializable接口，然后使用InputStream 和OutputStream 构造一个新的对象
     * @param: [obj]
     * @return java.lang.Object
     * @author zhuwh
     * @date 2018/7/20 11:29
     */
    public static Object deepCopy(Object obj){
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bo);
            objectOutputStream.writeObject(obj);

            ByteArrayInputStream bio = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(bio);

            return objectInputStream.readObject();


        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return  obj;
    }

    public static void main(String []args){
        User user = new User("lican",23);
        User user1 = (User)deepCopy(user);
        //hashcode不一样
        System.out.println("user hashcode:" + user.hashCode());
        System.out.println("user1 hashcode:" + user1.hashCode());

        System.out.println("==" + user.equals(user1));
        System.out.println("user:" + user.toString());
        System.out.println("user1:" + user1.toString());

    }
}

    class User implements Serializable {

        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
