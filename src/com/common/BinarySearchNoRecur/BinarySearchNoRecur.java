package com.common.BinarySearchNoRecur;

public class BinarySearchNoRecur {
    public static void main(String[] args){
        int[] arr={1,3,8,10,11,67,100};
        int index=binarySearch(arr,8);
        System.out.println("index="+index);


    }
    /***
     *
     *      二分查找非递归
     *      arr 待查找数组
     *      target 需要查找的数
     *      返回对应下标,-1表示没找到
     */

    public static int binarySearch(int[] arr,int target){
        int left=0;//下标
        int right=arr.length-1;//下标
        //初始时, left 和 right 分别表示 数组的头尾
        // 每次查找取一个区间,这个区间就是left和right

        while((left<=right)){
            int mid=(left+right)/2;//设置中间值,方便做中间量改变区间
            if(arr[mid]==target){
                return mid;//出口
            }else if(target<arr[mid]){//如果发现 待查数在左
                //mid-1然后赋给right
                right=mid-1;//向左边查找
            }else{
                left =mid+1;//向右查找
            }
        }
        return -1;
    }





}

























