package com.CodeRoot.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;//存储顶点
    private int[][] edges;//存储 邻接矩阵
    private int numOfEdges;//边数
    //定义数组boolean[],记录某个结点是否被访问过
    private boolean[] isVisited;

    public static void main(String[]args){

        int n=5;//节点个数
        String Vertexs[]={"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加结点
        for (String Vertex:Vertexs){
            graph.insertVertex(Vertex);
        }
        graph.insertEdge(0,1,1);//A-B
        graph.insertEdge(0,2,1);//A-C
        graph.insertEdge(1,2,1);//B-C
        graph.insertEdge(1,3,1);//B-D
        graph.insertEdge(1,4,1);//B-E

        graph.showGraph();
        // 测试dfs
        //graph.dfs();
        System.out.println();

        graph.bfs();


    }

    //构造器
    public Graph(int n){
        //初始化 矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<String>(n);
        numOfEdges=0;
        isVisited=new boolean[5];
    }

    /*** dfs
     *
     *  1) 访问初始结点 v ,并标记结点 v 为已访问
     *  2) 查找结点 v 的第一个邻接结点 w
     *  3) 如果 w 存在,则继续执行 4) ,反之,回到第一步,将从 v的下一个节点继续
     *  4) 如果 w 未被访问,对 w 进行深度优先遍历递归 (即把 w 当做另一个 v ,然后进行 步骤123
     *  5) 查找节点v的w邻接结点的下一个邻接结点 ,转到步骤 1
     *
     */
    //得到第一个邻接结点 的下标 w
    public int getFirstNeighbor(int index){
        for(int j=0;j<vertexList.size();j++){
            if(edges[index][j]>0){//有邻接点
                return j;//返回下标
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1,int v2){
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j]>0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited,int i){
        //首先输出该节点
        System.out.print(getValueByIndex(i)+" --> ");
        //将该节点设置为 为访问
        isVisited[i]=true;

        //查找结点 i 的第一个邻接结点 w
        int w=getFirstNeighbor(i);
        while(w!=-1){// w存在
             if(!isVisited[w]){
             //如果 w 未被访问,对 w 进行深度优先遍历递归 (即把 w 当做另一个 v ,然后进行 步骤123
                dfs(isVisited,w);
             }
             //如果 w结点已经被访问过
            w=getNextNeighbor(i,w);
        }
    }

    //dfs重载
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /***
     *
     *  bfs
     *  1)访问初始结点 v 并标记结点 v 为已访问
     *  2)结点 v 入队列
     *  3)当队列非空是,继续执行,否则 v 的算法结束
     *  4)出队列,取得队列头结点 u
     *  5)查找结点u的第一个邻接结点 w
     *  6)若结点 u 的邻接结点w 不存在,则转 步骤3 ,否则循环执行以下 三 步
     *      6.1 如果结点w 未访问, 则访问 结点w 并标记为已访问
     *      6.2 结点 w 入队列
     *      6.3 查找结点 u 的继 w  邻接结点后的下一个邻接结点 w,转到步骤 6
     *
     *
     *
     *
     */
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头结点对应的下标
        int w;//邻接结点的下标
        //队列,记录结点访问顺序
        LinkedList queue = new LinkedList();
        System.out.print(getValueByIndex(i)+" --> ");//输出该节点
        //标记已访问
        isVisited[i]=true;
        //将节点加入队列
        queue.addLast(i);
        while(!queue.isEmpty()){
            //取出队列的头结点下标
            u=(Integer) queue.removeFirst();
            //得到第一个邻接结点的下标w
            w=getFirstNeighbor(u);
            while(w!=-1){//找到了
                //判断是否访问过
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+" --> ");
                    isVisited[w]=true;//标记已访问
                    queue.addLast(w);
                }
                //以u为前驱结点 找w后面的下一个邻接结点
                w=getNextNeighbor(u,w);//体现出广度优先
            }
        }
    }
    //重载
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }


    //插入结点
    public  void insertVertex(String vertex){
        vertexList.add(vertex);

    }

    //得到结点数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边数
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点 i 对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1 ,int v2){
        return edges[v1][v2];
    }

    //打印 图对应的矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //添加边
    public  void insertEdge(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }

}
