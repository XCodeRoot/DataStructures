package LeetCode.Hot100._1两数之和;

import java.util.HashMap;
import java.util.Map;

public class _1 {


    class Solution {
        public int[] twoSum(int[] nums, int target) {

            //将每一个元素 , 使用(nums[i] , i)加入到 hashmap 里
            //遇到一个元素就判断能否添加进哈希表,不能添加表示匹配成功
            Map<Integer,Integer> hashTable=new HashMap<Integer,Integer>();
            for(int i=0;i<nums.length;i++){
                if(hashTable.containsKey(target-nums[i])){//如果包含,就表示匹配成功
                    return new int[]{hashTable.get(target-nums[i]),i};
                }
                //没重复出现,则继续添加进map里
                hashTable.put(nums[i],i);
            }
            return new int[0];



            // if(nums.length==2&&nums[0]+nums[1]==target){
            //     return new int[]{0,1};
            // }

            // for(int i=0;i<nums.length-1;i++){//左指针
            //     for(int j=i+1;j<nums.length;j++){//右指针
            //         if(nums[i]+nums[j]==target){
            //             return  new int[]{i,j};
            //         }
            //     }
            // }
            // return new int[0];
        }
    }

}
