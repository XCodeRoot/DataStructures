package OS.course.chapter1;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;



public class Priority {


    private static int count=1;//调度次数
    private final static int memorySize=5;//内存大小 , 一个pcb占用一个单位内存
    private static int pcbCount=0;//pcb个数


    public static void main(String[] args) {

        LinkedList<PCB> queue=new LinkedList<PCB>();


        if(pcbCount<memorySize){
            PCB process1=new PCB ("P1",2,1,1);
            queue.addLast(process1);
            System.out.println("第"+ ++pcbCount+"次成功为进程分配PCB,已用内存:"+pcbCount);

        }else{
            System.out.println("进程创建失败,可用内存不足以为进程创建pcb");
        }

        if(pcbCount<memorySize){
            PCB process5=new PCB ("P5",4,2,1);
            queue.addLast(process5);
            System.out.println("第"+ ++pcbCount+"次成功为进程分配PCB,已用内存:"+pcbCount);

        }else{
            System.out.println("进程创建失败,可用内存不足以为进程创建pcb");
        }


        if(pcbCount<memorySize){
            PCB process3=new PCB ("P3",1,3,1);
            queue.addLast(process3);
            System.out.println("第"+ ++pcbCount+"次成功为进程分配PCB,已用内存:"+pcbCount);

        }else{
            System.out.println("进程创建失败,可用内存不足以为进程创建pcb");
        }


        if(pcbCount<memorySize){
            PCB process4=new PCB ("P4",2,4,1);
            queue.addLast(process4);
            System.out.println("第"+ ++pcbCount+"次成功为进程分配PCB,已用内存:"+pcbCount);

        }else{
            System.out.println("进程创建失败,可用内存不足以为进程创建pcb");
        }


        if(pcbCount<memorySize){
            PCB process2=new PCB ("P2",3,5,1);
            queue.addLast(process2);
            System.out.println("第"+ ++pcbCount+"次成功为进程分配PCB,已用内存:"+pcbCount);

        }else{
            System.out.println("进程创建失败,可用内存不足以为进程创建pcb");
        }

        if(pcbCount<memorySize){
            PCB process6=new PCB ("P2",3,6,1);
            queue.addLast(process6);
            System.out.println("第"+ ++pcbCount+"次成功为进程分配PCB,已用内存:"+pcbCount);

        }else{
            System.out.println("进程创建失败,可用内存不足以为进程创建pcb");
        }


        run(queue);


    }

    public static void run(LinkedList<PCB> queue){

        while(!queue.isEmpty()){
            System.out.println("第"+count+"次调度");
            //根据优先数来调度进程
            Collections.sort(queue, new Comparator<PCB>() {
                @Override
                public int compare(PCB o1, PCB o2) {
                    return o2.getPriority()- o1.getPriority();
                }
            });

            System.out.println("{");
            //输出
            for (PCB pcb:queue){
                System.out.println(pcb.toString());
            }
            System.out.println("}");

            PCB pcb = queue.removeFirst();
            System.out.println("取出队首"+pcb.getProcessName());


            //正在运行
            System.out.println(pcb.getProcessName()+"正在运行");
            pcb.setPriority(pcb.getPriority()-1);
            pcb.setTime(pcb.getTime()-1);

            //检查是否还有时间

            if(pcb.getTime()<=0){//没时间就移除

                pcb.setStatus(0);
                System.out.println(pcb.getProcessName()+"已执行完毕");
                System.out.println("将"+pcb.getProcessName()+"状态设置为已结束");
                System.out.println("将"+pcb.getProcessName()+"进程撤销");
                System.out.println("归还所用内存,当前内存已用:"+ --pcbCount);

            }else{
                //还有时间就插入回队列
                queue.addLast(pcb);
                System.out.println(pcb.getProcessName()+"还没运行完再将"+pcb.getProcessName()+"加入队列");
            }



            count++;
            System.out.println();

        }
    }

}

class PCB { //为每个进程创建 空白PCB
    //进程名
    private String processName;
    //    //指向下一条进程的指针
//    private PCB next;
    //要求运行时间
    private int time;
    //优先数
    private int priority;
    //状态 1 表示就绪 , 0 表示结束
    private int status;

    public PCB(String processName, int time, int priority, int status) {
        this.processName = processName;
        //this.next = next;
        this.time = time;
        this.priority = priority;
        this.status = status;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

//    public PCB getNext() {
//        return next;
//    }
//
//    public void setNext(PCB next) {
//        this.next = next;
//    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PCB{" +
                "进程名='" + processName + '\'' +
                ", 要求运行时间=" + time +
                ", 优先数=" + priority +
                ", 状态=" + status +
                '}';
    }
}