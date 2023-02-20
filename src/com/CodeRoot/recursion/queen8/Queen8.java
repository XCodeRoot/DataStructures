package com.CodeRoot.recursion.queen8;

public class Queen8 {

    int max=8;
    /*  =======下标表示多少行，也表示第几个皇后，数组里的元素表示的是第几列有皇后==========*/
    int[] array=new int[max];//该数组保存 八皇后摆放的位置 如{0,4,7,5,2,6,1,3}
    int count=0;//全员变量，记录解的次数
    public static void  main(String[] args){

        Queen8 queen=new Queen8();
        queen.check(0);//0为第一个皇后


    }

    /**
     * 实际上
     *      第一个皇后（n=0，即第一行） 先摆第一列，然后n+1到第2个皇后（即n=1，第2行）
     *      第二个皇后从第1列开始摆，第一列摆不了，就顺推到第二列，第二列也不行就第3列，第3列似乎是可以的
     *      第三个皇后（即第三行的皇后）也从第一列开始，但是再n+1到第四第五个皇后发现不能摆了，每一列都冲突
     *      此时开始回溯到前一个皇后，继续将前一个皇后放在下一列，直到所有皇后均满足不冲突
     *
     *
     *
     *
     */
    //特别注意，每一次进入check 都有  for (int i = 0; i < max; i++) 所以会有回溯
    private void check(int n){//传入的是第n个待判断的皇后

        if(n==max){//n==8时，八个皇后就已经摆好了
            print();
            count++;
            System.out.println("当前是第"+count+"种解法");
            return;
        }
        /**
         *
         *
         *      选路过程中会产生简单的回溯，就是 当前皇后和前面所有皇后都冲突时，
         *      回溯到前一次的递归，继续原先未完成的for循环，把皇后摆在下一列
         *
         *      当 完成了一次正确的摆放位置 时，会进入上方的if里，打印出 八个皇后的位置
         *      然后进行回溯，会把前7个皇后锁住，将第8个皇后摆到下一列
         *      当第8个皇后的所有可以摆放的位置都试过了，就将第6个皇后锁住，将第7个皇后移到下一列，
         *      然后锁住第7个皇后，再遍历第8个皇后的所有可能的位置
         *
         *      直至回溯第一个皇后，
         *      此时应该已经把第一个皇后放在第1列 ，衍生的所有7个皇后的可能性全达成了
         *      然后就把第一个皇后移到第2列，反反复复
         *
         *
         */
        for (int i = 0; i < max; i++) {
            //先把当前这个第n个皇后放到该行的第一列
            array[n]=i;
            //然后判断，该皇后是否与前面的皇后冲突
            if(judge(n)){//如果判断出 该皇后不冲突则 继续放下一个皇后
                check(n+1);//从 n=1开始，一直循环找位置，然后就n++，再找位置，
            }

            //如果冲突 就循环一次（i++）把这个皇后放到 后一列
        }
    }



    //当摆放 第n个皇后时，判断是否与前面的皇后冲突（同行，同列，同斜线）
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //判断是否 同列   判断是否同斜线  abs(n-i)是行差 == 列差 abs(array[n]-array[i])
            //不用判断 是否同行，因为 n 既表示行，也表示现在是第n个皇后
            if(array[i]==array[n] ||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //打印八皇后位置
    private void print(){

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+ " ");
        }
        System.out.println();
    }

}
