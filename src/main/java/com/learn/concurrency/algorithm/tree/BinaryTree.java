package com.learn.concurrency.algorithm.tree;

import com.learn.concurrency.algorithm.Node;

/**
 * 二叉树
 * @author zhuwh
 * @date 2019/10/12 10:55
 * @desc
 */
public class BinaryTree {

    private Node tree;



    public Node find(int data){
        Node p = tree;
        while (p!=null){
            if(data<p.data) p =p.left;
            else if (data>p.data) p = p.right;
            else return p;
        }
        return null;
    }

    public void insert(int data){
        if(tree==null){
            tree = new Node(data);
        }
        Node p = tree;
        while (p!=null){
            if(data>p.data){
                if(p.right==null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }else{
                if(p.left==null){
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void delete(int data){
        Node p = tree;
        Node pp = null;
        while (p!=null&&p.data!=data){
            pp = p;
            if(data>p.data) p = p.right;
            else p = p.left;
        }
        if(p==null) return;

        //要删除的节点有两个子节点
        if(p.left!=null&&p.right!=null){ //查找右子树种最小的节点
            Node minP = p.right;
            Node minPP = p;
            while (minP.left!=null){
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; //将minP的数据替换到p中
            p = minP;
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    public Node findMax(){
        if(tree==null) return null;
        Node p = tree;
        while (p!=null){
            p = p.right;
        }
        return p;
    }

    public Node findMin(){
        if(tree==null) return null;
        Node p = tree;
        while (p!=null){
            p = p.left;
        }
        return  p;
    }
}
