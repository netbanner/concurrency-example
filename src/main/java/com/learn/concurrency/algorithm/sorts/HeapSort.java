package com.learn.concurrency.algorithm.sorts;

/**
 * @author zhuwh
 * @date 2019/10/17 15:39
 * @desc
 */
public class HeapSort {


    public static void sort(int []arr){
        if(arr.length<=1) return;

        //建堆
        buildHeap(arr);

        int k = arr.length-1;
        while (k>0){
            swap(arr,0,k);

            heapify(arr,--k,0);
        }

    }

    private static  void buildHeap(int []arr){
        for(int i = (arr.length-1)/2;i>=0;i--){
            heapify(arr,arr.length-1,i);
        }
    }

    private static void heapify(int []arr,int n,int i){

        while (true){
            //最大值位置
            int maxPos = i;
            //与左子节点(i*2+1)比较，获取最大值位置
            if(i*2+1<=n&&arr[i]<arr[i*2+1]){
                maxPos = i*2+1;
            }
            //最大值与右子节点(i*2+2)比较，获取最大值
            if(i*2+2<=n&&arr[maxPos]<arr[i*2+2]){
                maxPos = i*2+2;
            }
            //最大值是当前位置结束循环
            if(maxPos==i){
                break;
            }

            //与子节点交换
            swap(arr,i,maxPos);
            //以交换后子节点位置接下来往下找
            i = maxPos;
        }

    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
