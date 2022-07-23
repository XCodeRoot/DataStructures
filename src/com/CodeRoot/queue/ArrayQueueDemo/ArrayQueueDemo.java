package com.CodeRoot.queue.ArrayQueueDemo;
/**
 *
 *
 */


import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args){

        /**
         *    数组模拟队列
         *    1.先写一个ArrayQueue类
         */
        ArrayQueue queue = new ArrayQueue(3);
        char key=' ';
        Scanner scanner = new Scanner(System.in);//接收输入
        boolean loop=true;

        while(loop){

            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            System.out.println("选择操作：");
            key=scanner.next().charAt(0);//接收一个字符

            switch(key){

                case 's':
                    queue.showQueue();
                    break;

                case 'a':
                    System.out.println("输入一个数：");
                    int value=scanner.nextInt();
                    queue.addQueue(value);
                    break;

                case 'g':
                    /**
                     * 在try里执行时，遇到异常，则直接进入catch，用e.getMessage()打印错误信息
                     */
                    try {
                        int res=queue.getQueue();
                        System.out.println("取出的数是："+res+"\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage());//自动打印错误信息
                    }
                    break;

                case 'h':
                    try {
                        int res=queue.headQueue();
                        System.out.println("队列头部的数是："+res+"\n");
                    }catch (Exception e){
                        System.out.println(e.getMessage());//自动打印错误信息
                    }
                    break;

                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;

            }

        }
        System.out.println("程序退出");
    }
}

class ArrayQueue{

    /**
     *      private int front;//队列头部的前一个下标      front！=头部
     *      private int rear;//队列尾部下标             rear==尾部
     */
    //1.属性
    private int maxSize;//最大容量
    private int front;//队列头部的前一个下标  front！=头部
    private int rear;//队列尾部下标          rear==尾部
    private int[] arr;//存放数据，模拟队列

    //2.创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
        front=-1;//指向队列头部的那个数据的前一个位置:比如头部下标为0，则front=-1
        rear=-1;//指向队列尾部的那个数据
    }

    //3.判断队列是否满了
    public boolean isFull(){
        return rear==maxSize-1;
    }

    //4.判断是否为空队列
    public boolean isEmpty(){
        return front==rear;
    }

    //5.添加数据到队列
    public void addQueue(int n){

        if(isFull()) {
            System.out.println("不能再添加数据了");
            return;
        }
        rear++;
        arr[rear]=n;

    }

    //6.出队列
    public int getQueue(){

        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取数据");//throw就相当于打印这句话然后return
        }
        front++;//front后移，这样就方便取出头部
        return arr[front];

    }

    //7.显示队列
    public void showQueue(){
        
        if(isEmpty()){
            System.out.println("队列为空无法显示");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
        
    }

    //显示队列头部
    public int headQueue(){

        if(isEmpty()){
            throw new RuntimeException("队列为空无法显示");
        }
        return arr[front+1];
    }














}
