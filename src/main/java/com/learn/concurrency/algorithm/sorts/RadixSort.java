package com.learn.concurrency.algorithm.sorts;

/**
 *  基数排序
 * @author zhuwh
 * @date 2019/10/11 14:36
 * @desc
 */
public class RadixSort {

    public static void radixSort(int []arr){
        int max = arr[0];
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }

        for(int exp =1;max/exp>0;exp*=10){
            countingSort(arr,exp);
        }
    }
    /**
     * 计数排序-对数组按照"某个位数"进行排序
     * @param: [arr, exp] 数组，指数
     * @return void
     * @author zhuwh
     * @date 2019/10/11 14:53
     */
    public static void countingSort(int []arr,int exp){
        if(arr.length<=1){
            return;
        }

        int []c = new int [10];

        for(int i=0;i<arr.length;i++){
            c[(arr[i]/exp)%10]++;
        }

        //计算每个元素的个数
        for(int i=1;i<c.length;i++){
            c[i] += c[i-1];
        }

        int []r = new int[arr.length];

        // 临时数组r，存储排序之后的结果
        for(int i= arr.length-1;i>=0;i--){
            r[c[(arr[i]/exp)%10]-1] = arr[i];
            c[(arr[i]/exp)%10]--;
        }

        for(int i=0;i<arr.length;i++){
            arr[i] = r[i];
        }
    }
}
