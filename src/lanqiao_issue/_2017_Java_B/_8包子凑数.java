package lanqiao_issue._2017_Java_B;

import java.util.Arrays;
import java.util.Scanner;

public class _8包子凑数 {

    public static int gcd(int a,int b){//最大公约数
        if(b==0) return a;
        return gcd(b,a%b);
    }

    static int g;
    public static void main(String[] args) {
        /**
         *  用完全背包做:
         *      
         * 
          */
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        int[]arr=new int[N];
        for (int i = 0; i < N; i++) {
            arr[i]=sc.nextInt();
            if(i==0){
                g=arr[i];
            }else {
                g=gcd(arr[i],g);
            }
        }
        if(g!=1){
            System.out.println("INF");
            return;
        }
        Arrays.sort(arr);
/*
2
4
5
 */

        //完全背包
        // dp[i][j]语义为:j用arr里前i个数,能否表示出来
        boolean[][]dp=new boolean[N+1][100001];
        //初始化第一行
        for (int i = 1;  i <100001 ; i++) {
            if(arr[0]>i){//如果第一个笼子的容量大于顾客所需的容量,那么这就是装不下的
                dp[0][i]=false;
            }else {
                if(i%arr[0]==0){//可以整除
                    dp[0][i]=true;
                }else //不能整除
                    dp[0][i]=false;
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < 100001; j++) {
                if(j%arr[i]==0){
                    dp[i][j]=true;
                }else {
                    if(dp[i-1][j]==true){//如果上一行的同一位置 是可取的,那么当前格也一定是可取的
                        dp[i][j]=true;
                    }else {//如果上一行的同一位置 是不可取的,那么看相减以后是否可取
                        if(j-arr[i-1]>=1&&dp[i][j-arr[i-1]]==true){
                            dp[i][j]=true;
                        }else
                            dp[i][j]=false;
                    }

                }

            }
        }

        int ans=0;
        for (int i = 1; i <10001 ; i++) {//只看最后一行
            if(dp[N-1][i]==false){
                ans++;
            }

        }

        System.out.println(ans);


    }
    
    
}
