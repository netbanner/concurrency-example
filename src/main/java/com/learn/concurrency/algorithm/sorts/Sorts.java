package com.learn.concurrency.algorithm.sorts;

import java.util.Arrays;

/**
 * @author zhuwh
 * @date 2019/9/20 13:26
 * @desc
 */
public class Sorts {

    //冒泡排序
    public static void bubbleSort(int []a,int n){

        if(n<=1) return;

        for(int i=0;i<n;++i){
            //提前退出标志
            boolean flag = false;
            for(int j=0;j<n-1-i;++j){
                if(a[j]>a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag) break;
        }
    }


    //冒泡排序
    public static void bubbleSort2(int []a,int n){
        if(n<=1) return;
        // 最后一次交换的位置
        int lastExchange = 0;
        // 无序数据的边界,每次只需要比较到这里即可退出
        int sortBorder = n - 1;
        for(int i=0;i<n;i++){
            boolean flag =false;
            for(int j=0;j<sortBorder;j++){
                if(a[j]<a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag =true;
                    lastExchange =j;
                }
            }
            sortBorder = lastExchange;
            if(!flag) break;

        }
    }

    //插入排序
    public static void insertion(int []a,int n){
        if(n<=1) return;
        for(int i=1;i<n;++i){
            int value = a[i];
            int j = i-1;
            for(;j>=0;--j){
                if(a[j]>value){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = value;
        }
    }

    //选择排序
    public static void selectionSort(int []a,int n){
        if(n<=1) return;
        for(int i=0;i<n-1;++i){
            int minIndex = i;
            for(int j=i+1;j<n;++j){
                if(a[j]<a[minIndex]){
                    minIndex = j;
                }
            }
            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort2(array, array.length);
        System.out.println(Arrays.toString(array));
    }
}
