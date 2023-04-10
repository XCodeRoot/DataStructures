package lanqiao_issue._2021_Java_B;

import java.util.Scanner;

public class _08杨辉三角 {

    static long[][] dp;
    static int m=10000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N= sc.nextInt();
        /*

            一边用dp输入每个数到二维数组,同时 计数cnt,当前是第几个


            N<10^9
         */
        dp=new long[m][m];//含0下标
        for (int i = 0; i <m ; i++) {
            dp[i][0]=1;//每行第一列 为1
            dp[i][i]=1;

        }
        long sum=3;
        long cnt=1;
        for (int i = 2; i < m; i++) {
            for (int j = 1; j < i; j++) {
                if(i>=5&&j>=(i/2+2)){
                    break;
                }
                dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                cnt++;
                if(dp[i][j]==N){
                    System.out.println(sum+cnt);
                    return;
                }

            }
            cnt=1;
            sum+=i+1;
        }

    }
}
