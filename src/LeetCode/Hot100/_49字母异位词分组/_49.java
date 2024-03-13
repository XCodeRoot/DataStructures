package LeetCode.Hot100._49字母异位词分组;

import java.util.*;

public class _49 {
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            //遍历每个 字符串 , 将 字符串里的每个元素排序好 ,
            // 以有序的字符串 为唯一标识 作为key, 属于同一组字母异位词的字符串为value
            // 这样 value 就是 最终的答案
            Map<String,List<String>> map=new HashMap<String,List<String>>();
            for(String s :strs){
                //字符串转字符数组
                char[] chs=s.toCharArray();
                //字符数组排序
                Arrays.sort(chs);
                //转回字符串
                String key=new String(chs);
                //put
                List<String> list= map.getOrDefault(key,new ArrayList<String>());
                list.add(s);
                map.put(key,list);
            }
            return new ArrayList<List<String>>(map.values());//map转 二维列表

        }
    }

}
