package com.CodeRoot.Tree.BinarySortTreeDemo;

public class BinarySortTreeDemo {
    public static void main(String[] args){
        int[] arr={7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));//将数组元素 变成节点,导入到二叉排序树里
        }

        System.out.println("中序遍历二叉排序树");
        binarySortTree.inOrder();

        //
        System.out.println("测试删除叶子节点");
        binarySortTree.delNode(2);
        binarySortTree.inOrder();

        System.out.println("测试删除有一个子树的节点");
        binarySortTree.delNode(1);
        binarySortTree.inOrder();

        System.out.println("测试删除有两个子树的节点");
        binarySortTree.delNode(7);
        binarySortTree.inOrder();
    }

}
//二叉排序树
class BinarySortTree{
    private Node root;
    public void add(Node node){
        if(root==null){//如果树为空,则将第一个节点做根节点
            root=node;
        }else {
            root.add(node);
        }

    }

    public void inOrder(){
        if(root!=null){
            root.inOrder();
        }else {
            System.out.println("二叉排序树为空");
        }
    }
    //查找要删除的节点
    public Node Search(int value){
        if (root==null){
            return null;
        }else {
            return root.Search(value);
        }
    }
    //查找父节点
    public Node searchParent(int value){
        if(root==null){
            return null ;
        }else {
            return root.searchParent(value);
        }
    }

    //返回该二叉排序树的最小节点的值 并删除它
    public int delRightTreeMin(Node node){
        Node target=node;
        while(target.left!=null){
            target=target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //删除指定节点
    public void delNode(int value){
        if(root==null){
            return ;
        }else {
            Node targetNode=Search(value);
            if(targetNode==null){
                return;
            }
            //如果发现当前树左右一个节点
            if(root.left==null&&root.rigth==null){
                root=null;
                return;
            }
            //找父节点
            Node parent=searchParent(value);

            //如果待删除节点为叶子节点
            if(targetNode.left==null&&targetNode.rigth==null){
                //确定targetNode是parent的左子还是右子节点
                if(parent.left!=null&&parent.left.value==value){//如果是左子
                    parent.left=null;//则删除
                }else if(parent.rigth!=null&&parent.rigth.value==value){//右子
                    parent.rigth=null;
                }
            }
            //删除有两个子树的节点
            else if(targetNode.left!=null&&targetNode.rigth!=null){
                int minval= delRightTreeMin(targetNode.rigth);
                targetNode.value=minval;
            }
            else{//删除有一个子树的节点
                /**
                 *  5.如果target 有 左子节点
                 *  5.1 如果targetNode 是 parent的左子节点
                 *      parent.left=targetNode.left;
                 *  5.2 如果targetNode 是 parent的右子节点
                 *      parent.right=targetNode.left;
                 *  6. 如果target 有 右子节点
                 *  6.1 如果targetNode 是 parent的左子节点
                 *      parent.left=targetNode.right;
                 *  5.2 如果targetNode 是 parent的右子节点
                 *      parent.right=targetNode.right;
                 */
                //如果target 有 左子节点
                if(targetNode.left!=null){
                    if(parent!=null){
                        //如果targetNode 是 parent的左子节点
                        if(parent.left.value==value){
                            parent.left=targetNode.left;
                        }else {//如果targetNode 是 parent的右子节点
                            parent.rigth=targetNode.left;
                        }
                    }else{
                        root=targetNode.left;
                    }
                }else {//如果target 有 右子节点
                    if(parent!=null){
                        if(parent.left.value==value){//如果targetNode 是 parent的左子节点
                            parent.left=targetNode.rigth;
                        }else {//如果targetNode 是 parent的右子节点
                            parent.rigth=targetNode.rigth;
                        }
                    }else{
                        root=targetNode.rigth;
                    }
                }
            }

        }
    }
}

//节点
class Node{
    int value;
    Node left;
    Node rigth;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //递归添加节点(按二叉排序树规则)
    public void add(Node node){
        if(node==null){
            return;
        }
        //如果 要添加节点的值 小于 当前节点的值,则放左
        if(node.value<this.value){
            if(this.left==null){//如果左子为null
                this.left=node;
            }else{//否则就继续下一层,直到左子不为空
                this.left.add(node);
            }
        }else{//
            if(this.rigth==null){
                this.rigth=node;
            }else {
                this.rigth.add(node);
            }
        }

    }


    /**删除操作的三种情况
     *   一.待删除节点为叶子节点
     *      1.需要先去找到待删除的节点targetNode
     *      2.找到targetNode的父节点parent
     *      3.确定targetNode是parent的左子还是右子节点
     *   二.待删除节点 有一个子树
     *      1.需要先去找到待删除的节点targetNode
     *      2.找到targetNode的父节点parent
     *      3.确定targetNode有左子节点还是右子节点
     *      4.确定targetNode是parent的左子还是右子节点
     *      5.如果target 有 左子节点
     *        5.1 如果targetNode 是 parent的左子节点
     *            parent.left=targetNode.left;
     *        5.2 如果targetNode 是 parent的右子节点
     *            parent.right=targetNode.left;
     *      6.如果target 有 右子节点
     *        6.1 如果targetNode 是 parent的左子节点
     *            parent.left=targetNode.right;
     *        5.2 如果targetNode 是 parent的右子节点
     *          parent.right=targetNode.right;
     *   三.待删除节点 有两个子树
     *      1.需要先去找到待删除的节点targetNode
     *      2.找到targetNode的父节点parent
     *      3.从targetNode的右子树找到最小的节点
     *      4.用一个临时变量temp,将其保存
     *      5.targetNode.value=temp;
     */

    /** 查找要删除的节点
     *
     * @param value
     * @return
     */
    //查找要删除的节点
    public Node Search(int value){
        if(value==this.value){
            return this;
        }
        else if(value<this.value){//如果要查找的值 小于当前值,则往左子树找
            if(this.left==null){
                System.out.println("找不到");
                return null;
            }
            return this.left.Search(value);
        }else {//如果要查找的节点不小于当前节点,就向右子树查找
            if(this.rigth==null){
                return null;
            }else {
                return this.rigth.Search(value);
            }
        }
    }
    //待删除节点 的父节点
    public Node searchParent(int value){
        if((this.left!=null&&this.left.value==value)||
                (this.rigth!=null&&this.rigth.value==value)){
            return this;
        }else {
            //如果待查找的值 小于 当前节点的值,并且当前节点的左子节点不为空
            if(value<this.value&&this.left!=null){
                return this.left.searchParent(value);//向左子树递归查找
            }
            else if(value>=this.value&&this.rigth!=null){
                return this.rigth.searchParent(value);//向右子树递归查找
            }else {
                System.out.println("找不到父节点");
                return null;
            }
        }
    }


    public void inOrder(){
        if(this.left!=null){
            this.left.inOrder();
        }
        System.out.println(this);
        if(this.rigth!=null){
            this.rigth.inOrder();
        }
    }



}