package com.CodeRoot.sort.BubbleSort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args){

        int[] arr={3,9,-1,10,2};
        System.out.println("原数组\n"+Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后数组\n"+Arrays.toString(arr));

        int[] arr1=new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i]=(int)(Math.random()*80000);
        }
        // 大量样本的排序 所消耗的时间
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ms");
        String date1Str=simpleDateFormat.format(date1);
        System.out.println("8万次排序前时间: " +date1Str);

        bubbleSort(arr1);

        Date date2 = new Date();
        String date2Str=simpleDateFormat.format(date2);
        System.out.println("8万次排序后时间: " +date2Str);


    }

    public static void bubbleSort(int[] arr){
        int temp=0;
        boolean flag=false;//标志变量，判断是否进行过交换
        for (int i = 0; i < arr.length-1; i++) {//排4趟

            for (int j = 0; j <arr.length-i-1 ; j++) {//如果逆序就两两交换，然后比较后两个是否逆序
                //每完成一趟交换，得出一个最大数，排在右边
                //每完成一趟，就少比较一次 ，因为右边已经是正序的
                if(arr[j]>arr[j+1]){
                    flag=true;//标志变量，判断是否进行过交换
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }

//            System.out.println("第"+(i+1)+"趟排序后的数组");
//            System.out.println(Arrays.toString(arr));

            if (flag==false){//如果这一趟下来没交换过，就说明已经是正序的了
                break;
            }else {
                flag=true;//还原标志变量
            }
        }

    }
}
