package lanqiao_issue._2013_Java_B;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class _07错误票据 {

    public  static  void  main(String[] args){
        /**
         * 1.接收N行的数据,此数据为字符串
         * 2.split分隔字符串,转成int
         * 3.每个数都加到ArrayList里
         * 4.排序
         *
         */
        /*
2
5 6 8 11 9
10 12 9
         */
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        sc.nextLine();//吃掉输入的换行符
        ArrayList<Integer> list=new ArrayList<Integer>();//未知长度的数组 可以用ArrayList来装
        for (int i = 0; i < N; i++) {
            String[] s=sc.nextLine().split(" ");//接收一整行的输入,然后以 空格为分隔符,存入String[]数组里
            for (int j = 0; j < s.length; j++) {//遍历字符串数组
                list.add(Integer.parseInt(s[j]));//把字符串数组里的每个字符串,转换成整型,再添加到Arraylist里
            }

        }
        Collections.sort(list);//对于数组来排序用Arrays , 对于集合来进行排序就是用 Collections
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        int m=0,n=0;
        for (int i = 0; i < list.size(); i++) {
            if(i+1<list.size()&&list.get(i+1).equals(list.get(i))){//判断两个Integer对象的数值
                // 要用equals,因为双等号比较对象 比较的是两个对象的地址是否相等
                // Obj的 equals方法本身 比较的是对象的地址是否相等,而且子类Integer重写了equals方法,比较的是两个对象的内容是否相等
                n= list.get(i+1);//重号
            }
            else if(i+1<list.size()&&list.get(i+1)- list.get(i)!=1){//这里两个Integer对象相减,减号是被重载过的,所以可以直接进行数值计算

                m=list.get(i+1)-1;//断号
            }
        }


        System.out.println(m+" "+n);


    }

}
