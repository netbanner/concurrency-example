package com.learn.concurrency.algorithm.sorts;

/**
 * @author zhuwh
 * @date 2019/10/10 15:03
 * @desc 找出第k小的数据
 */
public class KthSmallest {

    public static int kthSmallest(int []arr,int k){
        if(arr==null||arr.length<k){
            return  -1;
        }
        int partition = partition(arr ,0,arr.length-1);

        while (partition+1!=k){
            if(partition+1<k){
                partition = partition(arr,partition+1,arr.length-1);
            }else{
                partition = partition(arr,0,partition-1);
            }
        }

        return arr[partition];
    }

    private static int partition(int arr[],int p,int r){
        int pivot = arr[r];
        int i =p;
        for(int j=p;j<r;j++){
            if(arr[j]<=pivot){
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,r);
        return i;
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
