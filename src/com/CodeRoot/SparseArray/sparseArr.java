package com.CodeRoot.SparseArray;

public class sparseArr {
    /**
     * 稀疏数组
     */
    public static void main(String[] args){
        /**
         *  创建一个原始二维数组 11*11
         *  0：表示没有棋子
         *  1：黑子
         *  2：白子
         *
         *  [row][col]
         *  （1）创建原始数组，并设置好棋子
         *  （2）遍历一遍看看原始数组,顺便统计有效棋子的个数
         *  （3）根据有效棋子个数，创建稀疏数组
         *  （4）将二维数组转换成稀疏数组
         *
         */
        int chessArr[][]=new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        chessArr[3][4]=1;
        System.out.println("原始二维数组");

        int sum=0;//记录 有效数字的个数
        for(int[] row:chessArr){
            for(int data:row){
                System.out.printf("%d\t",data);//这里用格式化输出 printf
                if(data!=0){
                    sum++;//统计 有效数字的个数
                }
            }
            System.out.println("\n");
        }

        System.out.println("sum = "+ sum);//稀疏数组的行 数等于sum加1
        //创建对应的稀疏数组
        int sparseArr[][]=new int[sum+1][3];//稀疏数组的列 始终为3
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;

        //二维数组转换成稀疏数组
        int temp=1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j <11 ; j++) {
                if(chessArr[i][j]!=0){
                    sparseArr[temp][0]=i;
                    sparseArr[temp][1]=j;
                    sparseArr[temp][2]=chessArr[i][j];
                    temp++;
                }
            }
        }

        System.out.println("下面是稀疏数组");
        for(int[] row:sparseArr){
            for(int data:row){
                System.out.printf("%d\t",data);//这里用格式化输出 printf
            }
            System.out.println("\n");
        }

//----------------下面是稀疏数组转二维数组---------------------
        /**
         * （1）创建新的二维数组
         * （2）一层循环遍历稀疏数组（因为稀疏数组的列是永远固定的，不需要遍历列
         * （3）只遍历 行，把这一行的三个数据，添加到二维数组里，稀疏数组的第一列表示棋子对应二维数组里的位置
         * （4）遍历输出新的二维数组
         */

        int chessArr2[][]=new int[ sparseArr[0][0] ] [ sparseArr[0][1] ];//创建新的二维数组
        for (int i = 1; i < sum+1; i++) {//遍历 稀疏数组
            chessArr2[ sparseArr[i][0] ] [ sparseArr[i][1] ]=sparseArr[i][2];//三合一，将行、列、值全用上
        }

        System.out.println("下面是稀疏数组转换成的二维数组");
        for (int[] row:chessArr2) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println("\n");
        }



    }

}
