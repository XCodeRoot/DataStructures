package OS.course.chapter2;

import javax.print.attribute.standard.Finishings;

import static java.lang.Thread.sleep;

public class bank {


    private static final int A=10;// A类资源共10个
    private static final int B=5;// B类资源共5个
    private static final int C=7;// C类资源共7个



    public static void main(String[] args) {

        //使用二维数组模拟 5个进程的 最大需求矩阵 Max
        int[][] Max=new int[][]{    {7,5,3},
                                    {3,2,2},
                                    {9,0,2},
                                    {2,2,2},
                                    {4,3,3} };// Max

        //使用二维数组模拟 5个进程的 分配矩阵 Allocation
        int[][] Allocation=new int[][]{    {0,1,0},
                                            {2,0,0},
                                            {3,0,2},
                                            {2,1,1},
                                            {0,0,2} };//

        //使用二维数组模拟 5个进程的 需求矩阵 Need
        int[][] Need=new int[5][3];

        //使用二维数组模拟 5个进程的 可利用资源向量Available
        int[] Available=new int[]{3,3,2};
        //使用二维数组模拟 5个进程的 可利用资源向量Work
        int[][] Work=new int[5][3];

        //求得 Need需求矩阵
        for(int i=0;i<Max.length;i++){
            for (int j=0;j<Max[0].length;j++){
                Need[i][j]=Max[i][j]-Allocation[i][j];
            }
        }

        System.out.println("need矩阵");
        System.out.println();

        for(int i=0;i<Max.length;i++){
            for (int j=0;j<Max[0].length;j++){
                System.out.print(Need[i][j]+" ");
            }
            System.out.println();
        }




        //seq(Max,Available,Need,Allocation,Work);

        int[] Request=new int[]{1,0,2};//模拟请求
        int process=1;//是 P1进程 , 发出请求
        System.out.println("P"+process+"进程请求资源"+"(1,0,2)\n");
        banker(process,Request,Max,Available,Need,Allocation,Work);

    }



    //银行家算法
    private static void banker(int process , int[] Request,int[][] Max,int[] Available,int[][] Need,int[][] Allocation,int[][] Work) {//银行家算法
        //1.根据请求判断 need 和 Available
        //2.通过后 , 修改 Available=Available-request ; Allocation=Allocation+request ; Need=Need-request
        //3.根据此时的 参数求得安全序列

        for(int i=0;i<Max[0].length;i++){
            if(Request[i]>Need[process][i]){
                System.out.println("请求的资源超过它所需的最大资源");
                return;
            }
            if(Request[i]>Available[i]){
                System.out.println("目前尚无足够资源, P"+process+"须等待");

                try {
                    sleep(1000000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        //资源足够就开始 分配
        for (int i=0;i<Max[0].length;i++){
            Available[i]=Available[i]-Request[i];
            Allocation[process][i]=Allocation[process][i]+Request[i] ;
            Need[process][i]=Need[process][i]-Request[i];
        }

        //然后求安全序列

        safety(Max,Available,Need,Allocation,Work);




    }

    //求安全序列 (安全性检查)
    private static void safety(int[][] Max,int[] Available,int[][] Need,int[][] Allocation,int[][] Work  ){
        int count=0;//统计当前循环到哪个进程
        boolean[] finish=new boolean[5];
        boolean flag=false;//退出循环的条件
        boolean escape=false;



        while(flag!=true){//当五个进程全都被分配了资源,就退出


            if(!finish[count]){ //如果当前进程没完成
                //判断当前 Available 是否大于 Need
                escape=false;
                for(int i=0;i<Max[0].length;i++){
                    if (Available[i]<Need[count][i]){
                        escape=true;//小于就无法分配 , 退出
                        break;
                    }
                }
                if(escape){//如果 资源不足 , 就直接进入下一次循环
                    count=(count+1)%5;
                    continue;
                }

                //假装分配
                finish[count]=true;
                System.out.println("分配资源给p"+count+"进程");
                System.out.println("回收资源");

                //此刻分配资源情况
                System.out.println("资源分配情况\t\tWork\t\tNeed\t\tAllocation\t\tWork+Allocation\t\tFinish");
                System.out.print("p"+count+"进程");


                //----------------------------------------
                //work
                System.out.print(" \t\t(");
                for(int j=0;j<Max[0].length;j++){
                    Work[count][j]=Available[j];
                    System.out.print(Work[count][j]);
                    if(j!=Max[0].length-1){
                        System.out.print(",");
                    }
                }
                System.out.print(")"+"\t");

                //Need
                System.out.print(" \t\t(");
                for(int j=0;j<Max[0].length;j++){
                    System.out.print(Need[count][j]);
                    if(j!=Max[0].length-1){
                        System.out.print(",");
                    }
                }
                System.out.print(")"+"\t");

                //Allocation
                System.out.print(" \t\t(");
                for(int j=0;j<Max[0].length;j++){
                    System.out.print(Allocation[count][j]);
                    if(j!=Max[0].length-1){
                        System.out.print(",");
                    }
                }
                System.out.print(")"+"\t");

                //work+Allocation
                System.out.print(" \t\t(");
                for(int j=0;j<Max[0].length;j++){
                    Work[count][j]=Work[count][j]+Allocation[count][j];
                    Available[j]=Work[count][j];
                    System.out.print(Work[count][j]);
                    if(j!=Max[0].length-1){
                        System.out.print(",");
                    }
                }
                System.out.print(")"+"\t");


                //finish
                System.out.print(" \t\t(");

                System.out.print(finish[count]);
                System.out.print(")"+"\t");
                //------------------------------------------

                System.out.println();
                System.out.println();





            }


            flag=true;//重置标记
            for (int i=0;i<Max.length;i++){

                if(finish[i]==false){//只要有一个进程没有结束 , 就进行循环, 如果全结束 , 就可以退出循环了
                    flag=false;
                }//为true则退出循环
            }
            count=(count+1)%5;// 保证计数  在 0~4 之间循环


        }
    }
}
