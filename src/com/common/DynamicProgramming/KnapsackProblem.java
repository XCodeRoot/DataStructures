package com.common.DynamicProgramming;

public class KnapsackProblem {

    public static void main(String[] args) {

        /**
         *  零一背包问题
         *
         *  先建表,画表格 有三个数组 v[i]商品价值 ,
         *                       w[i]商品容量 ,
         *                       v[i][j] j 容量下的商品最大价值解
         */
        int[] w = {1, 4, 3};//商品重量
        int[] val = {1500, 3000, 2000};//商品价值
        int m = 4;//背包容量
        int n = val.length;//物品的个数

        //二维数组 存放最大价值解
        int[][] v = new int[n + 1][m + 1];//每一个v[i][j] 都表示为在j容量时 前i个商品的最大价值解

        //为了记录放入商品的情况,我们定一个二维数组
        int[][] path = new int[n + 1][m + 1];


        //初始化第一行第一列 ,默认为零
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //开始动态规划 ,涉及到 val[] 和 w[] 两个数组时,要区别于 v[][] ,他们的 循环起始值不同
        for (int i = 1; i < v.length; i++) {//不处理第一行 i从第一行开始
            for (int j = 1; j < v[0].length; j++) {//不处理第一列 j从第一列开始
                if (w[i - 1] > j) {//如果当前商品重量 大于 背包容量
                    //因为w和循环变量i的起始值不同
                    v[i][j] = v[i - 1][j];//直接使用列方向上,上一单元格的最大价值解
                } else {//套公式v[i][j]=max{v[i-1][j] , v[i]+v[i-1][j-w[i]}
                    //因为w[]和val[]数组的 下标起始值为0,而v[][] 的是1
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);

                    //为了记录商品存放到背包的情况,我们不能简单的使用上述公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        //输出一下
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //遍历path[][],这样会输出所有放入情况,其实我们只需要最后的放入
//        for(int i=0;i<path.length;i++){
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j]==1)
//                System.out.printf("前%d个商品放入到背包\n",i);
//            }
//        }
        int i = path.length - 1;//行下标最大值
        int j = path[0].length - 1;//列下标最大值
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("前%d个商品放入到背包\n", i);
                j = j - w[i - 1];
            }
            i--;
        }


    }


}
