package com.CodeRoot.linkedlist.SingleLinkedList;

import java.util.Stack;

/**
 *      带头结点的单链表
 */
public class SingleLinkedListDemo {

    public static void main(String[] args){

        //以节点类 创建 结点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");


        //创建 链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //将结点 添加 到链表
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //变更信息
        HeroNode newheroNode = new HeroNode(2,"小卢","玉麒麟");
        singleLinkedList.update(newheroNode);

        //删除结点
        singleLinkedList.delete(hero3);

        //统计有效结点个数
        System.out.println("有效结点的个数 "+getLength(singleLinkedList.getHead()));

        singleLinkedList.show();

        //查找倒数第k个结点
        System.out.println("倒数第1个结点"+findLastIndexNode(singleLinkedList.getHead(),1));

        //链表倒转
        reverseList(singleLinkedList.getHead());
        singleLinkedList.show();
    }

    //静态方法
    //显示结点个数
    public static int getLength(HeroNode head){
        /**
         *  传入一个 头结点，返回有效结点个数
         */

        if(head.next==null){
            System.out.println("空链表");
            return 0;
        }
        int length=0;
        HeroNode cur=head.next;
        while(cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }

    //查找链表倒数第 k 个结点
    /** 1.接收head 和 index（倒数第index个结点）
     *  2. 用getLength获取链表长度，用它减去index再加 1
     *  3.找到就返回该结点，否则返回null
     */
    public static HeroNode  findLastIndexNode(HeroNode head,int index){
        if(head.next==null){
            return null;
        }
        HeroNode cur=head.next;//因为head是头结点，不能算进有效结点里
        int size =getLength(head);
        if(index<=0||index>size){
            return null;
        }
        for(int i=0;i<size-index;i++){

            cur=cur.next;
        }
        return cur;

    }

    //链表 反转
    public static void reverseList(HeroNode head){
        if(head.next==null||head.next.next==null){
            System.out.println("无法反转链表");
        }

        HeroNode cur=head.next;//定义一个辅助指针，遍历原链表,此时cur指向的是第一个结点
        HeroNode next=null;//定义一个结点，它指向当前结点的下一结点，因为当我们把当前结点头插到新链表时，
                           //原链表会直接断开，这时需要提前设好next结点，便于存好下一结点的数据
        //设置新链表的头结点
        HeroNode reverseHead=new HeroNode(0," "," ");

        /**
         *  遍历原来的链表，每遍历一个结点，就将其取出，头插到新链表的头结点之后
         */
        while(cur!=null){//遍历完就退了
            next=cur.next;//后移

            cur.next=reverseHead.next;//第一次循环，这里的reverse.Head.next为空，
                                    // 后面的循环，reverse.Head.next指向的都是倒转了一部分的新链表
            reverseHead.next=cur;//头插 一步一步将单个节点头插进
            //上面两条语句构成头插关键

            cur=next;//

        }
        head.next=reverseHead.next;//完成倒转
    }

    //逆序打印链表
    public static void reversePrint(HeroNode head){
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur=head.next;

        while (cur!=null){//将链表的所有结点从头到尾 压入栈中
            stack.push(cur);
            cur=cur.next;
        }
        while (stack.size()>0){//循环 将栈顶的结点取出
            System.out.println(stack.pop());
        }

    }

}

// 功能类
class SingleLinkedList{
    // 头结点不能 随意变动,作属性
    private HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    /***
     *
     *      构建链表的每个方法的统一思路:
     *      1.要查询到节点的方法,统一 采用while(true)永真循环来通过信号变量(flag真假)判断所处位置(可能是表尾还没找到,也可能找到)
     *        找到后退出循环,然后采用当前存留的cur指针访问需要的节点
     *
     *      2.有 cur=head + cur.next==null的组合 ,此适用于让头结点参与循环 方便操作的情况:增 删 改
     *        也有 cur=head.next + cur==null的组合 ,此适用于不想让头结点参与循环的情况:查 计数
     */



    //添加 结点
    public void add(HeroNode heroNode){
        /** 不考虑插入顺序，只进行尾插时
         *  1.将尾结点的next指向新结点
         */
        HeroNode temp=head;//临时变量，复制head
        //遍历找到 尾结点
        while (true){//永真循环必须有一个退出条件
            if(temp.next==null){
                break;
            }
            temp=temp.next;//如果 没遍历到尾结点，就将 temp往后移一位

        }
        temp.next=heroNode;
    }

    //按编号顺序 添加
    public void addByOrder(HeroNode heroNode){
        /**
         *  先遍历到要插入的位置（需要一个if比较前后元素
         *  再让  新结点.next指向后面的结点
         *  temp.next指向 新结点
         */
        HeroNode temp=head;
        Boolean flag=false;//作为标志，判断添加的编号是否存在，默认为否
        while (true){
            if(temp.next==null){//如果遍历到了，链表的最后
                //此时temp.next==null，让heroNode.next=temp.next是没有问题的
                break;
            }
            if(temp.next.no>heroNode.no){
                /**
                 * 此时 在插入位置有 前结点：temp ，后结点 temp.next，判断大小的时候已经把新结点夹住了
                 * 所以是这样只判断后结点的大小就行
                 **/
                // 从小到大排序，判断新结点的值是否小于 后继结点的值,如果是，就插入到temp后面
                //注意链接前后的顺序，先把新结点的 next域 链接到链表里，反之，会丢失所有后继结点的数据
                break;
            }
            if(temp.next.no==heroNode.no){
                //说明编号已经存在
                flag=true;
                break;
            }
            temp=temp.next;//遍历，持续后移
        }

        if(flag){
            System.out.printf("准备插入的 英雄的编号 %d 已经存在\n",heroNode.no);
        }else {
            heroNode.next=temp.next;
            temp.next=heroNode;
        }

    }

    //更改信息
    public void update(HeroNode newheroNode){
        if(head==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head;
        Boolean flag=false;//表示是否找到该结点
        while(true){
            if(temp.next==null){
                break;//遍历完成
            }
            if(temp.no== newheroNode.no){
                flag=true;//找到了
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name= newheroNode.name;
            temp.nickname= newheroNode.nickname;
        }else{
            System.out.println("未找到该英雄");
        }

    }
    //删除结点
    public void delete(HeroNode heroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head;
        Boolean flag=false;//标记是否有该结点
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==heroNode.no){//尤为关键的，当temp.next.no==HeroNode.no，
                // 所以我们就找到了 待删除结点的前一个结点的位置
                flag=true;
                break;
            }
            temp=temp.next;

        }
        if(flag){
            temp.next=temp.next.next;
        }else {
            System.out.println("未找到要删除的结点");
        }

    }



    //显示链表
    public void show(){

        //如果 头结点的next为空，则 为空链表
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //需要辅助变量来遍历
        HeroNode temp=head.next;//这里让temp指向 head.next,因为不打印头结点
        while(true){
            //判断是否到达链表最后
            if(temp==null){//这里是 temp而非temp.next，因为上面的辅助变量temp指向的是头结点的next，先判断这个temp是否为空
                //可以把head.next代进temp试试，所以单一个temp是对的
                break;
            }
            System.out.println(temp);
            //再让辅助变量后移
            temp=temp.next;
        }

    }



}

//结点类
class HeroNode {
    /**
     * HeroNode类 的每个对象 都是一个结点
     */
    public int no;//排名
    public String name;//名字
    public String nickname;//昵称
    public HeroNode next;//next指针，指向下一结点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }//不能加 next打印，因为这样会 把next指向的所有结点全部打印多次
}
