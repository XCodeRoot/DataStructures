package com.CodeRoot.Tree.ArrBinaryTreeDemo;

import java.util.Arrays;

public class ArrBinaryTreeDemo {
    public static void main(String args[]){
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }

    /***\\
     *      顺序存储二叉树 实际上 就是把数组 变成二叉树
     *
     *      方法就是用到前中后序遍历 的 preOrder(index*2+1)  向左递归
     *                             preOrder(index*2+2)  向右递归
     *
     *                            其中 index*2 才是数组体现二叉树特效的关键
     */
}
class ArrBinaryTree{
    private int[] arr;//存储节点的数组


    public ArrBinaryTree(int[] arr){
        this.arr=arr;
    }

    //重载该方法 ,不同的是参数,所以构成重载
    public void preOrder(){
        this.preOrder(0);
    }
    //顺序存储二叉树的前序遍历
    public void  preOrder(int index){
        if(arr==null&&arr.length==0){
            System.out.println("数组为空");
        }
        //输出当前元素
        System.out.println(arr[index]);

        //向左递归遍历
        if((index*2+1)<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if((index*2+1)<arr.length){
            preOrder(2*index+2);
        }
    }

}