package lanqiao_issue._2019_Java_B;

public class _04数的分解 {
    public static void main(String[] args) {
        /**
         *  用的还是三重循环无序排列
         */
        int ans=0;
        for (int i = 1; i <=2019 ; i++) {
            for (int j = 1; j <=2019; j++) {
                if(j>i){
                    int k=2019-i-j;
                    if(k>j){
                        if(check(i,j,k)) {
                            System.out.printf("%d,%d,%d\n", i, j, k);
                            ans++;
                        }
                    }

                }
            }
        }
        System.out.println(ans);

    }

    private static boolean check(int i, int j, int k) {
        int temp=0;
        while(i>0){
            temp=i%10;
            i/=10;
            if(temp==2||temp==4){
                return false;
            }
        }
        while(j>0){
            temp=j%10;
            j/=10;
            if(temp==2||temp==4){
                return false;
            }
        }
        while(k>0){
            temp=k%10;
            k/=10;
            if(temp==2||temp==4){
                return false;
            }
        }
        return true;
    }



}
