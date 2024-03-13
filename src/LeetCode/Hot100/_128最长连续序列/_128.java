package LeetCode.Hot100._128最长连续序列;

import java.util.HashSet;
import java.util.Set;

public class _128 {

    class Solution {
        public int longestConsecutive(int[] nums) {
            //1. 使用 set 去重
            //2.遍历set集合 ,  如果 当前数-1 出现在set集合里, 那么就跳过,
            // 因为我们一定在 当前数-1的时候 甚至之前 , 就已经判断过长度了

            Set<Integer> set=new HashSet<Integer>();
            for(int num:nums){
                set.add(num);
            }

            int longest=0;

            for(int i:set){

                //这样真的能保证 只有n个数会进入while或者for
                if(!set.contains(i-1)){ //剪枝 :剪去 当前数的前驱存在的情况 ,

                    int num=i;//当前数
                    int k=1;//记录以 i为起点的 最长长度

                    while(set.contains(num+1)){
                        k++;
                        num++;
                    }

                    longest=Math.max(longest,k);
                }
            }

            return longest;

        }
    }
}
