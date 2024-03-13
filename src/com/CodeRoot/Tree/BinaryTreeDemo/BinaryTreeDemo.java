package com.CodeRoot.Tree.BinaryTreeDemo;

public class BinaryTreeDemo {
    public static void main(String args[]){
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        //手动 构建树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);


        binaryTree.setRoot(root);
        binaryTree.preOrder();

        //接收前序遍历找到的该节点
//        HeroNode resNode= binaryTree.preOrederSearch(5);
//        if(resNode!=null){
//            System.out.printf("找到了,信息为 no=%d name=%s\n",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("没有找到 no=%d 的英雄\n",resNode.getNo());
//        }
//
//        HeroNode resNode1= binaryTree.postOrederSearch(5);
//        if(resNode1!=null){
//            System.out.printf("找到了,信息为 no=%d name=%s\n",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("没有找到 no=%d 的英雄\n",resNode.getNo());
//        }

        //删除节点
        binaryTree.delNode(5);
        System.out.println("删除后");
        binaryTree.preOrder();
    }

}
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空,无法遍历");
        }
    }
    public void inOrder(){
        if(this.root!=null){
            this.root.inOrder();
        }else {
            System.out.println("二叉树为空,无法遍历");
        }
    }
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //删除节点
    public void delNode(int no){
        if(root!=null){
            if(root.getNo()==no){
                root=null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树");
        }
    }


    //前序遍历查找
    public HeroNode preOrederSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode inOrederSearch(int no){
        if(root!=null){
            return root.inOrderSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode postOrederSearch(int no){
        if(root!=null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

    //删除叶子节点或者左右子树

}


class HeroNode{
    private int  no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +

                '}';
    }
    //遍历
    public void preOrder(){
        System.out.println(this);

        if(this.left!=null)
            this.left.preOrder();

        if (this.right!=null)
            this.right.preOrder();

    }
    public void inOrder(){
        if(this.left!=null)
            this.left.preOrder();

        System.out.println(this);

        if (this.right!=null)
            this.right.preOrder();
    }

    public void postOrder(){
        if(this.left!=null)
            this.left.preOrder();

        if (this.right!=null)
            this.right.preOrder();

        System.out.println(this);
    }

    /**
     * 删除 叶子节点或者左右子树
     */
    public void delNode(int no){
        if(this.left!=null&& this.left.no==no){
           this.left=null;
           return;
        }
        if(this.right!=null&&this .right.no==no){
            this.right=null ;
            return;
        }

        //如果前面两步没有删除节点,那么就进行递归 继续删除操作
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }




    }

    /**
     * 前序遍历查找
     *
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("前序遍历~~");
        if(this.no==no){//此判断语句 放在前中后,则代表前中后序遍历
            return  this;
        }
        //判断当前左子节点是否为空,如果不为空 则递归查找
        HeroNode resNode=null;//每种遍历都需要 接收变量,用于判断是否找到
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if(resNode!=null){//如果左子树找到了该节点
            return resNode;
        }
        if(this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode inOrderSearch(int no){

        //判断当前左子节点是否为空,如果不为空 则递归查找
        HeroNode resNode=null;//每种遍历都需要 接收变量,用于判断是否找到
        if(this.left!=null){
            resNode=this.left.inOrderSearch(no);
        }
        if(resNode!=null){//如果左子树找到了该节点
            return resNode;
        }

        System.out.println("中序遍历~~");
        if(this.no==no){//此判断语句 放在前中后,则代表前中后序遍历
            return  this;
        }

        if(this.right!=null){
            resNode=this.right.inOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode postOrderSearch(int no){

        //判断当前左子节点是否为空,如果不为空 则递归查找
        HeroNode resNode=null;//每种遍历都需要 接收变量,用于判断是否找到
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if(resNode!=null){//如果左子树找到了该节点
            return resNode;
        }

        if(this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if(resNode!=null){//如果右子树找到了该节点
            return resNode;
        }

        System.out.println("后序遍历~~");
        if(this.no==no){
            return this;
        }
        return resNode;
    }
}














