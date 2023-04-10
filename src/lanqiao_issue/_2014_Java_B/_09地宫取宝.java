package lanqiao_issue._2014_Java_B;

import java.util.Scanner;
import java.util.*;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class _09地宫取宝 {

    private static final int MOD = 1000000007;
    static int n;
    static int m;
    static int k;
    static int[][] arr;

    static long[][][][] cache=new long[50][50][14][12];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();//行
        m = sc.nextInt();//列
        k = sc.nextInt();//刚好取k件宝物
/*
2 3 2
1 2 3
2 1 5

 */
        
        /***
         *
         *      1.只能向右向下走
         *      2.去的那个格子要比 走过的 所有的 格子 更 大
         *      3.要刚好拿到k件宝物
         *      4.对符合条件的路线数 取模
         *
         *      一般遇到这种题,走迷宫之类的,用dfs,深度优先,走完所有路线
         *      先用构建最简单的dfs,就是向右向下走这一个动作,可以生成多少路线
         *      然后再对 上述情况下的路线数 进行条件限制:满足条件2,3
         */
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();//初始化迷宫
            }
        }

        for (int i = 0; i < 50; i++) {//初始化 记忆型 四维数组
            for (int j = 0; j < 50; j++) {
                for (int l = 0; l < 14; l++) {
                    for (int o = 0; o < 12; o++) {
                        cache[i][j][l][o]=-1;
                    }
                }
            }
        }

        long ans = dfs(0, 0, -1, 0);// x y max count
        System.out.println(ans);

    }


    // x y 为坐标, max为当前走过的最大值 , count表示当前路线取了多少件宝物
    private static long dfs(int x, int y, int max, int count) {

        if(cache[x][y][max+1][count]!=-1){//记忆查询,如果该位置已经有过记忆,则直接返回该位置可走的路线数
            return cache[x][y][max+1][count];
        }

        //防御
        if (x == n || y == m || count > k) {//边界条件
            return 0;
        }

        int cur = arr[x][y];
        long ans = 0;

        if (x == n - 1 && y == m - 1) {//到达终点
            if (count == k || (count  == k-1 && cur > max)) {
                //1.取到终点的宝物才刚好达到k个宝物 2.不取终点的宝物就已经到达k个宝物了

                return 1;
            }
            return 0;
        }


        if (cur > max) {//可以取当前格的宝物
            ans += dfs(x, y + 1, cur, count + 1);
            ans += dfs(x + 1, y, cur, count + 1);
        }
        ans += dfs(x, y + 1, max, count);//没取
        ans += dfs(x + 1, y, max, count);

        cache[x][y][max+1][count]=ans%MOD;//记忆  当前位置 可走的路线数
        return ans%MOD;


    }


}

