package lanqiao_issue._2015_Java_B;


import java.util.*;
public class _07牌型总数 {



    public static void main(String[] args) {

        /**     扑克牌 牌型 4*(A 2 3 4 5 6 7 8 9 10 J Q K) =4*13=52
         *      52张主牌加 大小王副牌 共 54张牌
         *
         *  1. 红桃 (红心 13张)
         *  2. 黑桃 (黑心 13张)
         *  3. 方块 (红方块 13张)
         *  4. 梅花 (黑三叶 13张)
         *  5. 大小王 (2张)
         *
         *
         */

        f(0,0);
        System.out.println(ans);



    }
    /**
     * 不考虑花色 只考虑点数 即 1 2 3 4 ...10 J Q K
     *          把给自己的13张牌 的每张牌,当成一个容器,逆向思维
     *          A里有0~4张牌 , 2 里有0~4张牌 , 3 ......
     *          共 5^13次方张牌
     *
     */
    private static int ans;

    // k 记录装容器的次数,1~13次 , count 记录装了多少张牌
    public static void f(int k,int count){
        if(count>13||k>13) return;
        if (count==13&&k<=13){//只有在确定好一种 符合要求的牌型时,ans才自增
            ans++;
            return;
        }

        for (int i = 0; i < 5; i++) {

            f(k+1,count+i);
        }

    }

}

