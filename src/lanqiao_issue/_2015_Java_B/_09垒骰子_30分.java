package lanqiao_issue._2015_Java_B;

import java.util.*;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class _09垒骰子_30分 {


    static boolean[][] conflict;
    static int[] op;
    static int MOD=1000000007;
    public static void main(String[] args) {
        /**
2 1
1 2
         */
        /**     思路
         *      1. 建立 对立面 的 映射关系
         *      2. 接收题目输入的 有冲突的面
         *      3. 暴力递归,for 枚举 最上层骰子 的顶面 ,确定好这第n层 就递归到下一层:n-1层,直到1层 共n层
         *      4. 递归过程 创建ans=0,同样枚举该层骰子的顶面,确定好改层就继续深入递归  ,
         *         递归深入到n=0层,就返回 1 ,即,组合数 +1,递归时要乘4,因为骰子可以旋转,不同朝向属于不同的面
         *
         */
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();

        conflict=new boolean[7][7];//接收a b, 记录 有冲突的面
        for (int i=0;i<m;i++){//接收m组 冲突面
            int a=sc.nextInt();
            int b=sc.nextInt();
            conflict[a][b]=true;
            conflict[b][a]=true;
        }

        op=new int[7]; // 顶面的对面的 值
        op[1]=4;
        op[2]=5;
        op[3]=6;
        op[4]=1;
        op[5]=2;
        op[6]=3;

        long ans=0;
        for (int up = 1; up <=6 ; up++) {//最顶层 有 6种选择,1-6 ,
            // 先固定最上层的骰子的顶面,再递归求解下面n-1层骰子的组合数

            ans=(ans+4*f(up,n-1))%MOD;//传入当前顶面, 共 n层骰子,
        }
        System.out.println(ans);

    }

    private static long f(int up, int count) {

        if(count==0){
            return 1;
        }

        long  ans=0;
        // up是上一层筛子的顶面 , upp是当前层筛子的顶面
        for (int upp = 1; upp <=6 ; upp++) {
            if(conflict[upp][op[up]])continue;//如果是符合冲突的 两个面 就继续循环找不回冲突的骰子顶面
            else {
                ans=(ans+4*f(upp,count-1))%MOD;
            }
        }
        return ans;


    }


}
