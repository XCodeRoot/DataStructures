package com.CodeRoot.sort.selectSort.select;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class selectSort {
    public static void main(String[]    args){


        int arr[]={101,34,119,1,5};
        System.out.println("排序前"+ Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后"+ Arrays.toString(arr));

        int[] arr1=new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i]=(int)(Math.random()*80000);
        }
        // 大量样本的排序 所消耗的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");
        String date1Str=simpleDateFormat.format(date1);
        System.out.println("8万次排序前时间: " +date1Str);

        selectSort(arr1);

        Date date2 = new Date();
        String date2Str=simpleDateFormat.format(date2);
        System.out.println("8万次排序后时间: " +date2Str);

    }

    /**
     *
     *      选择排序 就是
     *      1.从前5个数里，两两比较，找到最小数，和第一个数比较，把小数排到第一位（交换）
     *      2.从前4个数里，两两比较，找到最小数，和第二个数比较，把小数排到第二位（交换）
     *
     *      进入循环，假设当前数为最小数，重置最小数下标，如果两两比较 得到新的最小数，就记录这个下标
     *      排过一趟以后就 把当前最小数和第一个数交换
     */
    public static void selectSort(int [] arr){
        int minIndex=0;
        int min=arr[0];


        for (int i = 0; i < arr.length-1; i++) {
            minIndex=i;//重置 最小数下标
            min=arr[i];//假设当前数为 最小数
            for (int j = i+1; j <arr.length; j++) {
                if(min>arr[j]){
                    min=arr[j];
                    minIndex=j;//记录当前 最小数下标
                }
            }
            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
        }


    }

}
