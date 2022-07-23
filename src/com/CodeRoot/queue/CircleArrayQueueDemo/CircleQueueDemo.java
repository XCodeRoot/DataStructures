package com.CodeRoot.queue.CircleArrayQueueDemo;

import java.util.Scanner;

public class CircleQueueDemo {

    public static void main(String[] args) {

        /**
         * 数组 模拟 环形队列
         */
        CircleQueue queue = new CircleQueue(4);//说明: 数组容量为4，而队列 容量为3


        char key = ' ';
        Scanner scanner = new Scanner(System.in);//接收输入
        boolean loop = true;

        while (loop) {

            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头数据");
            System.out.printf("选择操作：");
            key = scanner.next().charAt(0);//接收一个字符

            switch (key) {

                case 's':
                    queue.showQueue();
                    break;

                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;

                case 'g':
                    /**
                     * 在try里执行时，遇到异常，则直接进入catch，用e.getMessage()打印错误信息
                     */
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数是：" + res + "\n");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());//自动打印错误信息
                    }
                    break;

                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("队列头部的数是：" + res + "\n");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());//自动打印错误信息
                    }
                    break;

                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }

        }
        System.out.println("程序退出");


    }


}

class CircleQueue {
    //1.
    private int maxSize;//最大容量
    private int front;// front指向队列的第一个元素，arr[front]就是数组的第一个元素
    private int rear;// rear指向队列的最后一个元素 的后一个位置 ，因为希望预留出一个空间，便于 判断环形队列为满时的条件
    //举例说，如果maxSize=3，那么当环形队列 状态为满时，rear指向 [2]的位置，也就是数组最后一位，但这一位实际上 并没有数据，
    //更重要的是，预留的这个位置，始终在发生变化

    private int[] arr;//存放数据，模拟队列

    //2.
    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        front=0;
        rear=0;
        arr=new int[maxSize];//浪费了我好几个小时，这个 限定队列 容量
    }

    //3.判断环形队列 是否 为满
    public boolean isFull() {
        return(rear + 1)%maxSize == front;
    }

    //4.判断是否为空队列
    public boolean isEmpty() {
        return front == rear;
    }

    //5.添加数据到队列
    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("不能再添加数据了");
            return;
        }

        arr[rear] = n;
        //rear后移，此时必须考虑 取模，因为是环形队列
        rear = (rear + 1) % maxSize;//满时，rear从 [最后一位]-->[0]
    }

    //6.出队列
    public int getQueue() {

        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");//throw就相当于打印这句话然后return
        }
        /** 这里需要分析出 front是指向环形队列的第一个元素，直接返回front则无法 将front后移
         *  1.先把front的值保存到一个临时变量里
         *  2.将front后移，考虑取模，因为仍然是环形
         *  3.将临时变量返回
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }

    //7.显示队列
    public void showQueue() {

        if (isEmpty()) {
            System.out.println("队列为空无法显示");
            return;
        }
        //从front开始遍历，
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
            /**
             *  这里用 i%maxSize，因为当 front在上，而rear在下的时候，遍历时，从front到顶部，再由底部往上到rear
             */
        }

    }

    //求当前队列有效数据的个数
    public int size() {
        /*
            front=0
            rear=0
            maxSize=10
         */
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头部元素
    public int headQueue() {

        if (isEmpty()) {
            throw new RuntimeException("队列为空无法显示");
        }
        return arr[front];
    }


}