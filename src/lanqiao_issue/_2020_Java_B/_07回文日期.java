package lanqiao_issue._2020_Java_B;



import java.util.*;
import java.math.*;


public class _07回文日期 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int date=sc.nextInt();

        /**
         *  枚举日期,
         *
         *  2.转回文
         *
         */
//12345678
        int[] monthOfDay={0,31,28,31,30,31,30,31,31,30,31,30,31};
        int y=date/10000;
        int m=date%10000/100;
        int d=date%100;
        boolean flag1=false;//是否找到回文日期
        boolean flag2=false;//是否找到ABABBABA日期
        for (int year = y; year <= 8999; year++) {
            if(isRun(year)){
                monthOfDay[2]=29;
            }else
                monthOfDay[2]=28;
            for (int month = m; month <= 12; month++) {

                for (int day = d+1; day <= monthOfDay[month]; day++) {
                    //如果 未进入过此语句,则进入, 曾进入过就跳过
                    if (flag1==false&&  isHuiwen(year*10000+month*100+day)==true){//判断是否是回文
                        flag1=true;
                        System.out.println(year*10000+month*100+day);
                        if(flag2==true &&flag1==true){
                            return;
                        }
                    }
                    if(flag2==flag1&& isABABBABA(year*10000+month*100+day)==true){//判断是否是ABABBABA型回文
                        flag2=true;
                        System.out.println(year*10000+month*100+day);
                        if(flag2==true &&flag1==true){


                            return;
                        }
                    }

                }
                d=1;

            }
            m=1;
        }


    }
    static boolean isRun(int year){
        if(year%400==0||(year%4==0)&&(year%100!=0)){
            return true;
        }else {
            return false;
        }
    }
    static boolean isHuiwen(int date){
        int temp=date;
        int sum=0;
        while(temp>0){
            sum*=10;
            sum+=temp%10;
            temp/=10;
        }
        if(sum==date){
            return true;
        }
        else
            return false;
    }
    static boolean isABABBABA(int date){
        if (isHuiwen(date)){//2020 0202
            int[] a=new int[4];
            int tmp=date;
            for (int i = 0; i < 4; i++) {
                a[i]=tmp%10;
                tmp/=10;
            }
            if(a[0]==a[2]&&a[1]==a[3]&&a[0]!=a[1]){
                return true;
            }

        }
        return false;
    }
}


