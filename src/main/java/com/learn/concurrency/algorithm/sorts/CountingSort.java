package com.learn.concurrency.algorithm.sorts;

/**
 * @author zhuwh
 * @date 2019/10/11 15:11
 * @desc
 */
public class CountingSort {

    public static void countingSort(int []a,int n){
        if(n<=1) return;

        // 查找数组中数据的范围
        int max = a[0];
        for(int i=1;i<n;++i){
            if(max<a[i]){
                max = a[i];
            }
        }

        // 申请一个计数数组c，下标大小[0,max]
        int []c = new int[max+1];

        // 计算每个元素的个数，放入c中
        for (int i = 0; i < n; ++i) {
            c[a[i]]++;
        }

        //依次累加
        for(int i=1;i<max+1;++i){
            c[i] = c[i-1]+c[i];
        }

        //临时数组r,存储排序后的结果
        int []r = new int[n];
        for(int i=n-1;i>=0;--i){
            int index = c[a[i]]-1;
            r[index] = a[i];
            c[a[i]]--;
        }

        //将结果拷贝到a数组
        for(int i=0;i<n;++n){
            a[i] = r[i];
        }

    }
}
