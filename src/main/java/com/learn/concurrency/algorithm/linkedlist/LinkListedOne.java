package com.learn.concurrency.algorithm.linkedlist;

/**
 * @author zhuwh
 * @date 2019/9/17 14:09
 * @desc
 */
public class LinkListedOne {


    public static class Node{
        private int data;
        private Node next;

        public Node(int data,Node next){
            this.data = data;
            this.next = next;
        }

        public int getData(){
            return data;
        }

        //单链表反序
        public static Node reverse(Node list){
            Node cur = list, pre = null;
            while (cur!=null){
                Node next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            return pre;
        }

        //检测环
        public static boolean checkCircle(Node list){
            if(list ==null) return false;

            Node fast = list.next;
            Node slow = list;

            while (fast!=null&&fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;
                if (slow==fast) return true;
            }

            return false;
        }

        public ListNode mergeTwoLists(ListNode l1,ListNode l2){
            ListNode soldier = new ListNode(0); //利用哨兵结点简化实现难度 技巧三
            ListNode p = soldier;
            while (l1!=null&&l2!=null){
                if(l1.val<l1.val){
                    p.next = l1;
                    l1 = l1.next;
                }else{
                    p.next = l2;
                    l2 = l2.next;
                }
                p = p.next;
            }
            if(l1!=null) { p.next = l1;}
            if(l2!=null) { p.next = l2;}


            return soldier.next;
        }

        public static Node deleteLastKth(Node list,int k){
            Node fast = list;
            int i=1;
            while (fast!=null&&i<k){
                fast = fast.next;
                ++i;
            }
            if(fast == null) return list;


            return fast;
        }
    }
}
