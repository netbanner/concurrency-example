package com.learn.concurrency.algorithm.queue;

/**
 * @author zhuwh
 * @date 2019/9/20 10:34
 * @desc 基于链表实现的队列
 */
public class QueueBasedOnLinkedList {


    private Node  head = null;
    private Node tail = null;

    public void enqueue(String value){
        if(tail==null){
            Node newNode = new Node(value,null);
            head = newNode;
            tail = newNode;
        }else{
            tail.next = new Node(value,null);
            tail = tail.next;
        }
    }

    public String dequeue(){
        if(head==null) return null;
        String value = head.data;

        head = head.next;
        if(head==null){
            tail = null;
        }
        return value;
    }


    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }
}
