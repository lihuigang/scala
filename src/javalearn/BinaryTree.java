package javalearn;




public class BinaryTree {

    // 根节点
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 插入操作
     *
     * @param value
     */
    public void insert(int value) {

        TreeNode newNode = new TreeNode(value);

        if (root == null) {
            root = newNode;
            root.setLefTreeNode(null);
            root.setRightNode(null);
        } else {

            TreeNode currentNode = root;
            TreeNode parentNode;

            while (true) {

                parentNode = currentNode;
                // 往右放
                if (newNode.getValue() > currentNode.getValue()) {
                    currentNode = currentNode.getRightNode();
                    if (currentNode == null) {
                        parentNode.setRightNode(newNode);
                        return;
                    }
                } else {
                    // 往左放
                    currentNode = currentNode.getLefTreeNode();
                    if (currentNode == null) {
                        parentNode.setLefTreeNode(newNode);
                        return;
                    }

                }
            }
        }
    }

    /**
     * 查找
     *
     * @param key
     * @return
     */
    public TreeNode find(int key) {

        TreeNode currentNode = root;

        if (currentNode != null) {

            while (currentNode.getValue() != key) {

                if (currentNode.getValue() > key) {
                    currentNode = currentNode.getLefTreeNode();
                } else {
                    currentNode = currentNode.getRightNode();
                }

                if (currentNode == null) {
                    return null;
                }

            }

            if (currentNode.isDelete()) {
                return null;
            } else {
                return currentNode;
            }

        } else {
            return null;
        }

    }

    /**
     * 中序遍历
     *
     * @param treeNode
     */
    public void inOrder(TreeNode treeNode) {
        if (treeNode != null && treeNode.isDelete() == false) {
            inOrder(treeNode.getLefTreeNode());
            System.out.println("--" + treeNode.getValue());
            inOrder(treeNode.getRightNode());
        }
    }

}


class Main {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        // 添加数据测试
        tree.insert(10);
        tree.insert(40);
        tree.insert(20);
        tree.insert(3);
        tree.insert(49);
        tree.insert(13);
        tree.insert(123);

        System.out.println("root=" + tree.getRoot().getValue());
        // 排序测试
        tree.inOrder(tree.getRoot());
        // 查找测试
        if (tree.find(10) != null) {
            System.out.println("找到了");
        } else {
            System.out.println("没找到");
        }
        // 删除测试
        tree.find(40).setDelete(true);

        if (tree.find(40) != null) {
            System.out.println("找到了");
        } else {
            System.out.println("没找到");
        }

    }

}
