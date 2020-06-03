package com.learn.concurrency.algorithm.sorts;

/**
 * @author zhuwh
 * @date 2019/10/10 13:54
 * @desc
 */
public class QuickSort {


    // 快速排序，a是数组，n表示数组的大小
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n-1);
    }

    private static void quickSortInternally(int []a,int p,int r){
        if(p>=r) return;

        int q = partition(a,p,r);
        quickSortInternally(a,p,q-1);
        quickSortInternally(a,q+1,r);
    }

    private static int partition(int []a,int p,int r){
        int pivot  = a[r];
        int i = p;
        for( int j= p;j<r;++j){
            if(a[j]<pivot){
                if(i==j){
                    ++i;
                }else{
                    int tmp = a[j];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }

        int tmp = a[i];
        a[i] = a[r];
        a[i] = tmp;
        System.out.println("i=" + i);
        return i;
    }
}
