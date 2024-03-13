package com.common.DividAndConquer;

public class hanoitower {

    public static void main(String[] args){

        hanoitower(64,'A','B','C');
    }

    /**
     *  汉诺塔
     *
     *  1)仅有一个盘时 ,A->C
     *
     *  2)有n>=2个盘时,我们总是可以看做仅有两个盘,一个是最下面的盘,另一个是上面所有的盘
     *     1.先把最上面的盘 A->B ,从A移到B
     *     2.把最下面的盘 A->C ,从A移到C
     *     3.把B塔的所有盘从B->C,从B移到C
     */

    public static void hanoitower(int num,char a,char b,char c){
        if(num==1){
            System.out.println("第1个盘从 "+a+"-->"+c);
        }else{
            //如果n>=2,我们总是可以看做仅有两个盘,一个是最下面的盘,另一个是上面所有的盘
            //1.先把最上面的盘 A->B ,将A的非底层盘 通过B 移到C
            hanoitower(num-1,a,c,b);
            //2.把最下面的盘 A->C ,从A移到C
            System.out.println("第"+num+"个盘从 "+a+"-->"+c);
            //3.把B塔的所有盘从B->C,从B移到C
            hanoitower(num-1,b,a,c);
        }
    }

}


