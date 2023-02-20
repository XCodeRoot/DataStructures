package lanqiao_test.Main;

public class Main_2 {
    public  static  void  main(String[] args){
        int [][]a=new int[100][100];
        for (int i = 0; i <100 ; i++) {
            for (int j = 0; j <100 ; j++) {
                a[i][j]=i+1+ 2*j;
            }
        }
        for (int i = 0; i < 95; i++) {
            for (int j = 0; j < 95; j++) {
                if(a[i][j]+a[i][j+1]+a[i][j+2]+a[i][j+3]+a[i][j+4]+a[i][j+5]+
                  a[i+1][j]+a[i+1][j+1]+a[i+1][j+2]+a[i+1][j+3]+a[i+1][j+4]+a[i+1][j+5]+
                  a[i+2][j]+a[i+2][j+1]+a[i+2][j+2]+a[i+2][j+3]+a[i+2][j+4]+a[i+2][j+5]+
                a[i+3][j]+a[i+3][j+1]+a[i+3][j+2]+a[i+3][j+3]+a[i+3][j+4]+a[i+3][j+5]+
                        a[i+4][j]+a[i+4][j+1]+a[i+4][j+2]+a[i+4][j+3]+a[i+4][j+4]+a[4][j+5]+
                a[i+5][j]+a[i+5][j+1]+a[i+5][j+2]+a[i+5][j+3]+a[i+5][j+4]+a[i+5][j+5]
                          ==2022)
                {
                    System.out.println(i+" ha "+j);
                }
            }
        }


    }
}
