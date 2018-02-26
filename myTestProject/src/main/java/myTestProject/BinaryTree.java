package myTestProject;

/**
 * 李政
 * 2018/1/10
 */
public class BinaryTree {

    // 根节点
    private TreeNode root = null;

    // 节点个数
    private Integer nodeCount = 0;

    // 内部类，树节点
    private static class TreeNode {
        Integer data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(Integer data) {
            this.data = data;
        }
    }

    // 插入
    public void insert(Integer data) {
        this.privateInsert(root, data);
    }

    private void privateInsert(TreeNode currentNode, Integer data) {
        if (root == null) {
            TreeNode newOne = new TreeNode(data);
            root = newOne;
            nodeCount++;
        } else {
            // 小的插到左节点
            if (data <= currentNode.data) {
                // 为空则插入，非空递归插入
                if (currentNode.leftChild == null) {
                    TreeNode newOne = new TreeNode(data);
                    currentNode.leftChild = newOne;
                    nodeCount++;
                } else {
                    privateInsert(currentNode.leftChild, data);
                }
            } else {
                if (currentNode.rightChild == null) {
                    TreeNode newOne = new TreeNode(data);
                    currentNode.rightChild = newOne;
                    nodeCount++;
                } else {
                    privateInsert(currentNode.rightChild, data);
                }
            }
        }

    }

    // 先序遍历
    public void preOrder() {
        this.privatePreOrder(this.root);
    }

    private void privatePreOrder(TreeNode current) {
        if (current == null) {
            return;
        }
        System.out.print(current.data + "  ");
        privatePreOrder(current.leftChild);
        privatePreOrder(current.rightChild);
    }

    // 中序遍历
    public void inOrder() {
        this.privateInOrder(this.root);
    }

    private void privateInOrder(TreeNode current) {
        if (current == null) {
            return;
        }
        privateInOrder(current.leftChild);
        System.out.print(current.data + "  ");
        privateInOrder(current.rightChild);
    }

    // 后序遍历
    public void postOrder() {
        this.privatePostOrder(this.root);
    }

    private void privatePostOrder(TreeNode current) {
        if (current == null) {
            return;
        }
        privateInOrder(current.leftChild);
        privateInOrder(current.rightChild);
        System.out.print(current.data + "  ");
    }

    // 获得节点个数
    public Integer getNodeCount() {
        return nodeCount;
    }

    // 获得树高度
    public Integer getHeight(){
        return this.privateGetHeight(root);
    }
    private Integer privateGetHeight(TreeNode node){

        if(node == null){
            return 0;
        }
        Integer l = privateGetHeight(node.leftChild);
        Integer r = privateGetHeight(node.rightChild);
        return (l<r)?(r+1):(l+1);
    }


    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.insert(6);
        binaryTree.insert(3);
        binaryTree.insert(14);
        binaryTree.insert(10);
        binaryTree.insert(9);
        binaryTree.insert(16);
        binaryTree.insert(13);
        binaryTree.insert(11);
        binaryTree.insert(12);

        binaryTree.preOrder();
        System.out.println("先序遍历结束");
        binaryTree.inOrder();
        System.out.println("中序遍历结束");
        binaryTree.postOrder();
        System.out.println("后序遍历结束");

        System.out.println("节点个数：" + binaryTree.getNodeCount());
        System.out.println("树高度：" + binaryTree.getHeight());
    }
}
