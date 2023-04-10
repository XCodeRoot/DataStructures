package lanqiao_issue._2013_Java_B;

import java.util.*;
public class _09带分数 {
    static int ans = 0;
    static int N = 0;
    static int cnt=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        f(arr, 0);
        System.out.println(ans);
        System.out.println("A 9 9 全排列总个数"+cnt);
    }

    //全排列
    public static void f(int[] arr, int k) { // k是全排列中第k位的数
        if (k == 9) {//确认边界
            check(arr);//每求出全排列的一种情况,此时k==9,进入check,枚举加号和除号的位置
            cnt++;
            return;
        }

        //全排列
        for (int i = k; i < arr.length; i++) { //全排列,在循环中递归,一步一步探出每一种可能,类似于一维数组的八皇后问题
            //将第i位和第k位交换位置
            int t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;

            //移交 下一层 去确认 第k+1位
            f(arr, k + 1);

            //回溯换回来
            t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;

        }

    }

    public static boolean check(int[] arr) {
        for (int i = 1; i <= 7; i++) {//加号只能放在 1~7的位置,枚举加号位置
            int num1 = toInt(arr, 0, i);// +号前面的数
            if (num1 >= N)//如果加号前面的数额已经超过N就没有必要再算了
                continue;
            //除号前面的字符数至少是一个,最大位置是 8
            for (int j = 1; j <= 8 - i; j++) {//枚举除号位置
                int num2 = toInt(arr, i, j);
                int num3 = toInt(arr, i + j, 9 - i - j);//除号后面的数
                if (num2 % num3 == 0 && num1 + num2 / num3 == N) {
                    ans++;
                }
            }
        }
        return true;
    }

    //把多个单数字,转成大数字
    public static int toInt(int[] arr, int pos, int len) {// pos起点,
        int t = 1;
        int sum = 0;
        for (int i = pos + len - 1; i >= pos; i--) {
            sum += arr[i] * t;
            t *= 10;
        }
        return sum;
    }

}