package OS.course.os_memoryManage;

import java.util.*;

public class memoryManage {


    /** TODO:
     *      1.创建进程时 输入进程大小 , 并根据程序中设定的物理块大小为进程分配物理块 , 创建进程可装入固定的前三页,其余页装入到置换空间内
     *      2.同时建立支持请求和置换功能的二维页表（增加存在位等）页表
     *      3.输入当前进程要访问的逻辑地址(页号) , 将其转为物理地址(内存中的物理块号)
     *      4.对地址转换过程中遇到的缺页现象进行页面置换
     *      5.可将多次地址转换过程中所涉及到的页号视为进程的页面访问序列，从而计算置换次数和缺页率。
     */

    public static final int BLOCK_SIZE=4096;//物理块大小为 4096 B
    public static final int BLOCK_NUM=64;//   物理块数为 64
    public static int[][] bitMap;// 位示图 , 代表64个内存物理块的分配情况
    public static final int PROCESS_SIZE=32767;//进程大小 32767 B

    public static void main(String[] args) {

        System.out.println("开始模拟内存分配");
        System.out.println("设定内存中物理块大小为"+BLOCK_SIZE + " B , 物理块数为64\n");
        //1.创建位示图 , 模拟内存占用情况
        bitMap=new int[8][8];
        //随机填充0或1
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            for (int j=0;j<8;j++){
                bitMap[i][j]=  random.nextInt(2);// 0,1 随机整数
            }
        }
        //打印位示图

        showBitMap();

//======================================================

        //2.创建进程, 进程要请求 一系列的 页号, 例如:  2,6,7,4,3,5,4,3,8,2,1,6,3,5,4,2,1
        //该进程共 32767字节, 所需8页
        int pageNum=  PROCESS_SIZE/BLOCK_SIZE+1;

        //如果进程大小超过总内存,就报错
        if(PROCESS_SIZE>BLOCK_SIZE*BLOCK_NUM){
            System.out.println("该进程大小大于总内存");
        }else{
            System.out.println("该进程大小 小于 内存大小,可以创建该进程");
        }

        //创建pcb
        PCB pcb = new PCB(PROCESS_SIZE,pageNum);// 32767/4096=7+1页
        System.out.println("创建进程,进程大小为"+BLOCK_SIZE +" B,分页数为 "+pageNum+" 页,为其分配5个物理块\n");


//========================================================



        //3.创建二级页表 , 仅用一个 外部页表 和 一个 内部页表
        List<Page> page=new ArrayList<>();//外部页表 (相当于目录)
        page.add(new Page(0,1011,1,7));//外部页表的一个页表项,存放的是某页表的起始地址,物理块号为1011

        //创建空页表,因为还没装入内存,所以页表是空的
        List<Page> page1=new ArrayList<>();//页表 (是内部页表 , 一个页表里,记录了某页在内存的物理块号 , 通过物理块号与页大小,页内地址算出物理地址)
        //除了外存地址需要分配,其余属性均为-1
        int external_count=1;
        for (int i = 0; i < 8; i++) {
            page1.add(new Page(i,-1,0, random.nextInt(5)+external_count++));
            external_count+=5;
        }

        //设置 请求的 页号 序列
        List<Integer> requests =new ArrayList<>(Arrays.asList(8190,23576,28001,16001,12270,20479
                                                            ,16381,12287,32767,4097,4000,22222
                                                            ,12200,20000,16383,5000,2023));//17次页面请求


//========================================================
        System.out.println("=============================================================");

        //4.我们预装入前三页 2,6,7  同步更新位示图与页表项
        List<Integer> firstThreePage = requests.subList(0, 3);

        System.out.println("预装入前三页到内存");



        //预装入前3页 , 更新位示图和页表项
        //获取位示图中前5个空闲的物理块 , 之后该进程只能使用这5个物理块, 通过页面置换算法来 置换 缺页
        List<Integer> firstFiveSpace=preLoad(firstThreePage,bitMap,page1,pageNum ); //返回值是位示图中空闲的前5个物理块的 块号
        if(firstFiveSpace.isEmpty()||firstFiveSpace==null){
            System.out.println("越界中断");
        }
        System.out.println("已将前三页装入进内存");

        //打印位示图
        showBitMap();

        //打印页表
        showPage(page1);
        System.out.println("=============================================================");
//=============================================================

        //5.开始读入后面的 14次页面请求
        //调用LRU算法,当发生缺页中断时,进行页面置换,当前进程所拥有的 物理块数为 5
        System.out.println("开始执行后面的14次页面请求\n");
        LRU(requests,bitMap,page1,pageNum,firstFiveSpace);


    }


    //方法
    //LRU算法,接收页面请求,置换功能
    private static void LRU(List<Integer> requests, int[][] bitMap, List<Page> page1, int pageNum, List<Integer> firstFiveSpace) {

        double count=0;//缺页数

        //新建链表,用于LRU算法
        List<Integer> LRU=new LinkedList<>();
        for (int i = 0; i < 3; i++) {//遍历前三次 逻辑地址的请求
            Integer pageNo = checkOutOfBound(requests.get(i), pageNum);//得到 页号
            if (!LRU.contains(pageNo)){//链表中不存在该页号
                LRU.add(pageNo);//添加进链表
            }else{
                //存在则删除原有的
                LRU.remove(pageNo);
                //重新添加
                LRU.add(pageNo);
            }

        }

        for (int i=3;i<requests.size();i++){//从第三次页面请求开始遍历
            //1.判断是否发生越界
            Integer pageNo = checkOutOfBound(requests.get(i), pageNum);
            if(pageNo==-1){
                System.out.println("发生越界中断");
                return;
            }
            System.out.println("===========第"+(i+1)+"次页面请求=========================");

            //2.判断该页是否已经在内存中
            if(LRU.contains(pageNo)){
                System.out.println("逻辑地址: "+requests.get(i)+", 对应页号为: "+pageNo+" ,未发生页号越界");
                System.out.println("该页已经在内存中\n");
                //刷新
                //存在则删除原有的
                LRU.remove(pageNo);
                //重新添加 到链表
                LRU.add(pageNo);

                showLRU(LRU,page1);//打印 LRU链表的页号 序列和 其对应的物理块

                System.out.println("===============================================\n\n");
                continue;
            }

            //不在内存,就判断 物理块是否用完, 还有空位就可以直接添加
            if (LRU.size()<5){
                //5个物理块 ,没用完
                //就直接添加进 LRU序列
                LRU.add(pageNo);

                //取出没用过的物理块
                Integer block=firstFiveSpace.get(LRU.size()-1);

                System.out.println("逻辑地址: "+requests.get(i)+", 对应页号为: "+pageNo+" ,未发生页号越界"
                        +" ,对应物理地址为: "+trans(requests.get(i),block));

                System.out.println("将外存的 "+page1.get(i).getExternal()+" 号物理块"+"读入到内存的 "+block+" 号物理块\n");

                //如果找到一个没有过的物理块,就用这个物理块

                System.out.println("修改位示图");
                bitMap[block/8][block%8]=0;//修改物理块为已占用
                System.out.println("修改页表");
                page1.get(pageNo).setStatus(1);//修改页表项的存在位
                page1.get(pageNo).setBlockNo(block);//修改页表项的物理块号

                showLRU(LRU,page1);//打印 LRU链表的页号 序列和 其对应的物理块

                showBitMap();
                showPage(page1);


                System.out.println("===============================================\n\n");
                count++;
                continue;
            }

            //如果 这 5个物理块都满了 , 就开始页面置换
            System.out.println("==========分配的物理块已用完 , 开始页面置换=========\n");
            Integer first = LRU.get(0);
            System.out.println("开始将"+first+"号页与缺页"+pageNo+"号页 进行页面置换");
            System.out.println("未发生页号越界");
            System.out.println("将"+first+"号页,写回外存");
            LRU.remove(first);//移除链表头部
            LRU.add(pageNo);//添加新的页号
            Integer block=page1.get(first).getBlockNo();

            System.out.println("逻辑地址: "+requests.get(i)+", 对应页号为: "+pageNo
                    +" ,对应物理地址为: "+trans(requests.get(i),block));

            //
            System.out.println("修改位示图");
            bitMap[block/8][block%8]=0;//修改物理块为已占用
            System.out.println("修改页表");

            page1.get(pageNo).setStatus(1);//修改 现 页表项 的存在位
            page1.get(pageNo).setBlockNo(block);//修改 现 页表项 的物理块号

            page1.get(first).setStatus(0);//修改 旧 页表项 的存在位
            page1.get(first).setBlockNo(-1);//修改 旧 页表项 的物理块号

            showLRU(LRU,page1);//打印 LRU链表的页号 序列和 其对应的物理块

            showBitMap();
            showPage(page1);
            count++;

        }

        System.out.println("\n缺页次数 : "+count+"次 , 缺页率: "+ count*100/17 +"%");

    }


    //方法
    //预装入前三页到内存 : 2,6,7
    private static List<Integer> preLoad(List<Integer> firstThreePage, int[][] bitMap, List<Page> page1 ,Integer pageNum) {

        //在位示图 查找空闲的 前5个物理块 , 1为空闲 , 0为占用 , 一共64个物理块,  块号=行号*8+列号
        List<Integer> firstFiveSpare = new ArrayList<>(5);
        int count=0;//计数
        for (int i = 0; i < 8; i++) {
            for (int j=0; j< 8;j++){
                if(count>4){
                    break ;
                }
                if(bitMap[i][j]==1){
                    //空闲块
                    firstFiveSpare.add(i*8+j);//添加物理块号 到数组 , 块号=行号*8+列号
                    count++;
                }
            }
            if(count>4){
                break ;
            }
        }
        System.out.println("找到前五个空闲块");

        //开始预装入前三页 2,6,7   修改位示图
        System.out.println("修改位示图");
        for (int i=0;i<3;i++){
            //传入逻辑地址,判断页号是否越界
            Integer pageNo = checkOutOfBound(firstThreePage.get(i), pageNum);
            if (pageNo==-1){
                return Collections.emptyList();
            }
            System.out.println("逻辑地址: "+firstThreePage.get(i)+", 对应页号为: "+pageNo +" ,未发生越界中断"
                    +" ,对应物理地址为: "+trans(firstThreePage.get(i),firstFiveSpare.get(i)));
            Integer number = firstFiveSpare.get(i);//获取物理块号
            System.out.println("将外存的 "+page1.get(i).getExternal()+" 号物理块"+"读入到内存的 "+number+" 号物理块\n");
            bitMap[number/8][number%8]=0;
        }


        //添加前三页到页表项 , 更新 页表项的 物理块号,存在位
        System.out.println("\n更新页表项信息,修改存在位");
        for (int i = 0; i < 3; i++) {
            Integer pageNo = checkOutOfBound(firstThreePage.get(i), pageNum);
            page1.get(pageNo).setBlockNo(firstFiveSpare.get(i));//物理块号
            page1.get(pageNo).setStatus(1);//存在位为1
        }


        //返回值是位示图中空闲的前5个物理块的 块号
        return firstFiveSpare ;
    }


    public static void showLRU(List<Integer> LRU,List<Page> page1){
        System.out.print("\n打印LRU算法记录的 页号序列: ");
        for (Integer pageNo : LRU) {//遍历LRU算法的 页号序列
            System.out.print(pageNo+" ");//打印页号

        }

        System.out.print("\n打印页号序列对应的 物理块号: ");
        for (Integer pageNo : LRU) {//遍历LRU算法的 页号序列

            //打印该页号对应的物理块号
            System.out.print(page1.get(pageNo).getBlockNo()+" ");
        }
        System.out.println();
    }



    //方法
    //传入逻辑地址,判断页号是否越界,未越界就返回逻辑地址对应的页号
    private static Integer checkOutOfBound(Integer logicalAdress,Integer pageNum) {
        Integer pageNo= logicalAdress/BLOCK_SIZE;

        if(pageNo>=pageNum||pageNo<0){
            System.out.println("发生越界中断,进程阻塞");
            return -1;
        }

        return pageNo;
    }



    //方法
    //地址变换机构 : 输入逻辑地址,物理块号 , 返回物理地址
    private static Integer trans(Integer logicalAdress,Integer blockNo) {
        Integer pageNo=logicalAdress/BLOCK_SIZE;//页号
        Integer offset=logicalAdress%BLOCK_SIZE;//页内地址
        Integer physicalAddress=blockNo*BLOCK_SIZE+offset;//物理地址


        return physicalAddress;
    }


    //方法
    //打印位示图
    public static void showBitMap(){

        System.out.println("下面是位示图");
        for (int i = 0; i < 8; i++) {
            for (int j=0;j<8;j++){
                System.out.print(bitMap[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //方法
    //打印页表
    public static void showPage(List<Page> page1){
        System.out.println("下面是页表");
        System.out.println("页号\t物理块号\t存在位\t外存物理块号");
        for (Page page : page1) {
            System.out.println(page.getPageNo()+"\t"
                    +page.getBlockNo()+"\t\t"
                    +page.getStatus()+"\t\t"
                    + page.getExternal());
        }
    }

}



class Page{ // 页表
    private int pageNo;//页号
    private int blockNo;//物理块号
    private int status;//存在位
    private int external;//外存地址 (磁盘的物理块号)

    public Page() {
    }

    public Page(int pageNo, int blockNo, int status, int external) {
        this.pageNo = pageNo;
        this.blockNo = blockNo;
        this.status = status;
        this.external = external;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getBlockNo() {
        return blockNo;
    }

    public void setBlockNo(int blockNo) {
        this.blockNo = blockNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getExternal() {
        return external;
    }

    public void setExternal(int external) {
        this.external = external;
    }
}



class PCB{//进程
    private int size;//进程大小 32767 B
    private int pageNum;//将该进程装入内存的所需页数 32767/4096 -> 共8页

    public PCB() {
    }

    public PCB(int size, int pageNum) {
        this.size = size;
        this.pageNum = pageNum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
