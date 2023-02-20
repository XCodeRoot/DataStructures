package com.CodeRoot.hashtab.HashTable;

import java.util.Scanner;

public class HashTable {
    public static void main(String args[]){
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单菜单

        String key="";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\nadd:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");

            System.out.println("exit:退出系统\n");

            key=scanner.next();//键入 字符串选项
            switch (key){

                case "add":
                    System.out.println("输入id");
                    int id=scanner.nextInt();//键入整型
                    System.out.println("输入名字");
                    String name=scanner.next();//键入字符串

                    Emp emp=new Emp(id,name);//创建雇员节点
                    hashTab.add(emp);//加到哈希表的某链表里
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id=scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.out.println("退出系统");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//哈希表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;//链表数组
    private int size;//表示多少条链表


    public HashTab(int size) {//初始化哈希表即 ,该 由链表组成的数组
        this.size=size;
        empLinkedListArray=new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i]=new EmpLinkedList();//给链表数组的每个元素创建链表空间
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id,得到该员工应该在哪一条链表里
        int empLinkedListNO=hashFun(emp.id);//调用取模法,得到散列值
        empLinkedListArray[empLinkedListNO].add(emp);//哈希表类的add方法调用了链表类的add方法
    }
    //遍历哈希表
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);//哈希表类的list方法调用了链表类的list方法
        }
    }

    //根据输入的id查找雇员
    public void findEmpById(int id){
        int empLinkedListNO=hashFun(id);//调用取模法,得到散列值
        Emp emp=empLinkedListArray[empLinkedListNO].findEmpById(id);//哈希表类的add方法调用了链表类
        if(emp!=null){//根据散列值找到
            System.out.printf("在第%d条链表中找到该雇员id=%d\n",(empLinkedListNO+1),id);
        }else {
            System.out.println("在哈希表中没找到该雇员");
        }
    }



    //编写散列函数,其中最简单的方法是取模法
    public int hashFun(int id){

        return id%size;
    }
}


//干员节点类
class Emp{

    public int id;
    public String name;
    public Emp next;//默认为null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


}
//链表类
class EmpLinkedList{
    private Emp head;

    //add
    //1.假定,当添加雇员时,id是自增长的,即id的分配总是从小到大
    //  因此直接尾插雇员
    public void  add(Emp emp){

        //如果添加的是第一个雇员
        if(head==null){
            head=emp;
            return;
        }
        //如果不是第一个雇员,则使用辅助指针,遍历定位到尾
        Emp curEmp=head;//辅助遍历的指针
        while(true){
            if(curEmp.next==null){
                break;
            }
            curEmp=curEmp.next;
        }
        curEmp.next=emp;
    }

    //list 遍历输出链表的雇员信息
    public void list(int no){//传入链表编号
        if(head==null){
            System.out.println("第"+(no+1)+"条链表为空");
            return;
        }
        System.out.printf("第"+(no+1)+"条链表信息为");
        Emp curEmp=head;
        while (true){
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            if(curEmp.next==null){
                break;
            }
            curEmp=curEmp.next;
        }
        System.out.println("");
    }

    //根据id查找雇员
    public Emp findEmpById(int id){
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp=head;
        while(true){
            if(curEmp.id==id){
                break;
            }
            if(curEmp.next==null){//遍历到尾还没找到
                curEmp=null;
                break;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }






}
















