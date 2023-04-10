package lanqiao_issue._2020_Java_B;

import java.util.Scanner;

public class _08数字三角形 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N= sc.nextInt();
        int[][]arr=new int[N][N];
        for (int i = 0; i <N; i++) {
            for (int j = 0; j <= i; j++) {
                arr[i][j]= sc.nextInt();
            }
        }
/*
5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5
 */
        int[][]dp=new int[N][N];//dp[i][j]的语义为,当从 [1][1]为起点,走到[i][j]的最大路径和

//        if(N%2==1){//奇
//            dp[N-1][N/2]=arr[N-1][N/2];
//        }else {//偶
//            dp[N-1][N/2]=arr[N-1][N/2];
//            dp[N-1][N/2-1]=arr[N-1][N/2-1];
//        }
        dp[0][0]=arr[0][0];
        for (int i = 1; i < N; i++) {//初始化最左列,因为在最左列时,只有一种走法
            dp[i][0]=dp[i-1][0]+arr[i][0];
        }
        for (int i = 1; i <N ; i++) {//跳过初始值,即,整个二维数组的第一列
            for (int j = 1; j <=i ; j++) {
                //从上往下
                dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-1])+arr[i][j];// 从左下和右下的方向选一个走

            }
        }
        if(N%2==1){//奇
            System.out.println(dp[N-1][N/2]);
        }else {//偶
            System.out.println(Math.max(dp[N-1][N/2],dp[N-1][N/2-1]));
        }


    }


}
