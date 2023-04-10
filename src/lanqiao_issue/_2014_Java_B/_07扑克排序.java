package lanqiao_issue._2014_Java_B;


import java.util.HashSet;
import java.util.Set;

public class _07扑克排序 {



    public static void main(String[] args) {
        char[] arr={'A','A','2','2','3','3','4','4'};



        f(arr,0);
        for (String s:set
        ) {
            System.out.println(s);
        }

    }
    //去重,把所有符合条件的字符串,加到Set集合里
    static Set<String> set=new HashSet<String>();//因为 Set集合不允许 添加重复的元素,所以可以实现去重


    //全排列 的模板
    public static void f(char[]arr,int k){//传入
        if(k==arr.length){
            String s=new String(arr);
            if(check(s)){
                //System.out.println(s);
                set.add(s);
            }
        }

        for(int i=k;i<arr.length;i++){
            char t=arr[i];
            arr[i]=arr[k];
            arr[k]=t;

            f(arr,k+1);//每次往 k+1的位置探

            t=arr[i];
            arr[i]=arr[k];
            arr[k]=t;

        }


    }
    //s.lastIndexOf("A") 返回字符 A 最后一次出现的 索引
    //s.indexOf("A") 返回字符 A 第一次出现的 索引
    public static boolean check(String s){
        if(s.lastIndexOf("A")-s.indexOf("A")==2&&
                s.lastIndexOf("2")-s.indexOf("2")==3&&
                s.lastIndexOf("3")-s.indexOf("3")==4&&
                s.lastIndexOf("4")-s.indexOf("4")==5){
            return true;
        }
        return false;

    }


}

