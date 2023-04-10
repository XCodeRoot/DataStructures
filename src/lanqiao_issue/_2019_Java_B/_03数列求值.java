package lanqiao_issue._2019_Java_B;

public class _03数列求值 {
    public static void main(String[] args) {
        /*
        1 1 1 3 5 9 17
         */
        int n=20190324;

        int a=1,b=1,c=1,d=1;
        for (int i = 3; i < n; i++) {
            a=b;
            b=c;
            c=d;
            d=(a+b+c)%10000;
        }
        System.out.println(d);
    }
}
