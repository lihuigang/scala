package javalearn;



public class BinaryTreeTest {

    // 根节点
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    private class TreeNode {

        // 左节点
        private TreeNode lefTreeNode;
        // 右节点
        private TreeNode rightNode;
        // 数据
        private Object value;

        private boolean isDelete;

        public TreeNode getLefTreeNode() {
            return lefTreeNode;
        }

        public void setLefTreeNode(TreeNode lefTreeNode) {
            this.lefTreeNode = lefTreeNode;
        }

        public TreeNode getRightNode() {
            return rightNode;
        }

        public void setRightNode(TreeNode rightNode) {
            this.rightNode = rightNode;
        }

        public Object getValue() {
            return value;
        }

        public int getValueHashCode(){
            return value.hashCode();
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public boolean isDelete() {
            return isDelete;
        }

        public void setDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public TreeNode() {
            super();
        }

        public TreeNode(Object value) {
            this(null, null, value, false);
        }

        public TreeNode(TreeNode lefTreeNode, TreeNode rightNode, Object value,
                        boolean isDelete) {
            super();
            this.lefTreeNode = lefTreeNode;
            this.rightNode = rightNode;
            this.value = value;
            this.isDelete = isDelete;
        }

        @Override
        public String toString() {
            return "TreeNode [lefTreeNode=" + lefTreeNode + ", rightNode="
                    + rightNode + ", value=" + value + ", isDelete=" + isDelete
                    + "]";
        }

    }

    public void insert(Object obj){
        TreeNode newNode = new TreeNode(obj);
        TreeNode currentNode=root;
       if(root == null) {
           root=newNode;
       }else{
           while(true){
               if(obj.hashCode() > currentNode.getValue().hashCode()){
                   if(currentNode.getRightNode() == null){
                        currentNode.setRightNode(newNode);
                        return;
                   }else{
                        currentNode = currentNode.getRightNode();
                   }
               } else{

                   if(currentNode.getLefTreeNode() == null) {
                       currentNode.setLefTreeNode(newNode);
                       return;
                   }else{
                       currentNode = currentNode.getLefTreeNode();
                   }
               }
           }
       }
    }

    public TreeNode find(Object obj){
        TreeNode currentNode =  root;
        if(root == null){
            return null;
        }
        while(currentNode.getValue()!=obj){
            if(obj.hashCode()>currentNode.getValueHashCode()){
                currentNode=currentNode.getRightNode();
            }else{
                currentNode=currentNode.getLefTreeNode();
            }
            if(currentNode==null){
                return null;
            }
        }

        return currentNode;
    }

    /**
     * 中序遍历
     *
     * @param treeNode
     */
    public void inOrder(TreeNode treeNode) {
        if (treeNode != null && treeNode.isDelete() == false) {
            System.out.println("++" + treeNode.getValue());
            inOrder(treeNode.getLefTreeNode());
            System.out.println("--" + treeNode.getValue());
            inOrder(treeNode.getRightNode());
        }
    }

    public static int f(int n) {
        if (1 == n)
            return 1;
        else
            return n*f(n-1);
    }

    public static void main(String[] args){
        BinaryTreeTest tree = new BinaryTreeTest();
        tree.insert("11");
        tree.insert("18");
        tree.insert("9");
        tree.insert("8");
        tree.insert("19");
        tree.insert("5");
        tree.insert("10");
        tree.insert("12");

        System.out.println("11".hashCode());
        System.out.println("12".hashCode());
        System.out.println("9".hashCode());

        System.out.println("n+"+f(5));

        tree.inOrder(tree.getRoot());

        System.out.println(tree.find("11"));
    }


}
