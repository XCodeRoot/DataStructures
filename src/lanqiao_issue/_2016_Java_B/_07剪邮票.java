package lanqiao_issue._2016_Java_B;

public class _07剪邮票 {

    static int ans=0;
    static int[] arr;
    static int[][] g;
    public static void main(String[] args) {
        /**
         *  先一维数组12个里选5个,无序 C 12 5
         *  对于每一个排列,我们用check(arr)进行连通性检验
         *      check(),把一维数组arr[5]   二维展开g[3][4]
         *      再枚举二维数组每一个非0点, 进入dfs()对四个方向进行深搜,
         *                  深搜条件:某方向上的点为非0点,即可走,若为0则为墙
         *                  深搜边界:二维下标不越界
         *                  回溯阶段:回到上一层递归,判断另外没走过的方向,是否有非0点
         *      dfs()深搜,用于连通性检验,因为只有 为非0点才能走,如果该排列连通,
         *      则一遍深搜就能把所有点变为0点,此时就找不到深搜目标(:以非0点的四个方向深搜)
         *      所以只要count深搜次数就能判断是否是连通的
         */
        arr=new int[5];
        g=new int[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {//初始化二维数组g
                g[i][j]=i*4+j+1;
            }
        }

        for (int i = 0; i < 12; i++) {//得到 C 12 5, 1-12个数里取5个不重复的数,不计顺序
            arr[0]=i+1;
            for (int j = 0; j < 12; j++) {
                if(j>i){
                    arr[1]=j+1;
                    for (int k = 0; k < 12; k++) {
                        if(k>j){
                            arr[2]=k+1;
                            for (int l = 0; l < 12; l++) {
                                if(l>k){
                                    arr[3]=l+1;
                                    for (int m = 0; m <12; m++) {
                                        if(m>l){//得到一组 5 个数的排列
                                            arr[4]=m+1;
                                            if (check(arr)){
                                                ans++;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ans);

    }

    private static boolean check(int[] arr) {

        int count=0;//枚举arr[]的5个元素
        for (int i = 0; i < 3; i++) {//将arr[5]展开成二维g[3][4]
            for (int j = 0; j < 4; j++) {
                if(g[i][j]==arr[count]){
                    g[i][j]=1;
                    count++;
                }else {
                    g[i][j]=0;
                }
            }
        }
        //现在 g[][]中有 5个元素为1,进行连通性检验
        int cnt=0;
        for (int i = 0; i < 3; i++) {//枚举每一个非0点
            for (int j = 0; j < 4; j++) {
                if(g[i][j]==1){
                    dfs(g,i,j);//以某个非0点为起点,对四个方向深搜,深搜时将走过的点置0
                    cnt++;//如果仅仅深搜一遍,则表明二维数组里的每个点都已经置0,也表明该排列是连通的
                }
            }
        }
        return cnt==1;//看看深搜是否是仅仅1遍

    }

    private static void dfs(int[][] g, int i, int j) {//对四个方向深搜,深搜时将走过的点置0,
        // 不用担心走过的路置0,因为回溯时会继续以剩余的方向进行深搜dfs()
        g[i][j]=0;

        //深搜顺序无关紧要,你懂的,还是因为回溯
        if( i-1>=0&&g[i-1][j]==1 )dfs(g,i-1,j);//上
        if( i+1<=2&&g[i+1][j]==1 )dfs(g,i+1,j);//下
        if( j-1>=0&&g[i][j-1]==1 )dfs(g,i,j-1);//左
        if( j+1<=3&&g[i][j+1]==1 )dfs(g,i,j+1);//右
    }


}
