package lanqiao_issue._2019_Java_B;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _02不同子串 {
    public static void main(String[] args) {
        /**
         *  1.for枚举左右边界
         *  2.字符串存入set里
         *
         */
        Scanner sc = new Scanner(System.in);
        String s=sc.next();
        Set<String>set=new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                set.add(s.substring(i,j+1));
            }
        }
        System.out.println(set.size());
    }
}
