package com.CodeRoot.recursion.Recursion;

public class MiGong {
    public static void main(String[] args){

        int[][] map=new int[8][7];
        //迷宫 上下全为 1
        for (int i = 0; i < 7; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右为1
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        // print
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");

            }
            System.out.println("\n");
        }

        setWay(map,1,1);
        System.out.println("小球走过的");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"\t");

            }
            System.out.println("\n");
        }
    }
    //递归给小球找路
    /**
     *  map表示地图
     *  i j  从[1][1]位置开始找
     *  map[i][j]=0时，则表明没走过该点，为 1 时 是墙，为 2时是通路，为3表示走过该点但是走不通
     *  如果找到通路map[6][5]返回true
     */

    public static boolean setWay(int[][]map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){//如果该点还没走过
                //按照策略：  下 -> 右 -> 上 -> 左
                map[i][j]=2;//假定可以走通
                /**
                 * 小球走的每一步都是 由if 里的递归算法得到的
                 *  当走到 if(setWay(map,i+1,j))时，进去，即向下走，然后递归，再进一个if(setWay(map,i+1,j))
                 *  如果走不通，就返回 false给上一层的if的括号里，这样就表示走不通，然后继续依次判断 下 左 右的路
                 */
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else {//回溯
                    map[i][j]=3;
                    return false;
                }
            }else {//如果map不为0，则可为 1,2,3，这三个全不能走
                return false;

            }
        }
    }

}
