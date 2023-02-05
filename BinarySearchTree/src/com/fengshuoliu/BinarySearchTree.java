package com.fengshuoliu;

import com.fengshuoliu.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树
 * 遍历的应用
 * 前序遍历:树状结构展示(注意左右子树顺序)
 * 中序遍历:二叉搜索树的中序遍历按升序或者降序处理
 * 后序遍历:适用于一些先子后父的操作
 * 层序遍历:计算二叉树的高度;判断一棵树是否为完全二叉树
 * @param <E>
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator; // 比较器

    public BinarySearchTree() {
        this(null);
    }
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public  void clear() {

    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个结点
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        // 添加的不是第一个节点
        /**
         * 添加步骤
         * 找到父节点 parent
         * 创建新节点 node
         * parent.left = node 或者 parent.right = node
         *
         * 遇到值相等的元素如何处理? 建议覆盖旧的值
         */
        Node<E> parent = root;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node; // 保存父节点
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                node.element = element;
                return;
            }
        }

        // 看看插入到父节点哪个位置
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return false;
    }

    /**
     * 前序遍历(Preorder Traversal): 根节点、前序遍历左子树、前序遍历右子树
     * 1.使用递归方法
     * 2.使用迭代方法
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    /**
     * 中序遍历(Inorder Traversal):中序遍历左子树、根节点、中序遍历右子树
     * 二叉搜索树的中序遍历结果是升序或者降序
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) return;

        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    /**
     * 后序遍历(Postorder Traversal):后序遍历左子树、后序遍历右子树、根节点
     */
    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    /**
     * 层序遍历(Level Order Traversal):从上到下、从左到右依次访问每一个节点
     * 实现思路:使用队列
     * 1.将根节点入队
     * 2.循环执行以下操作,知道队列为空
     *   将对头节点A出列,进行访问
     *   将A的左子节点入队
     *   将A的右子节点入队
     *
     *   要求能默出来
     */
    public  void levelOrderTraversal() {
        if (root == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 比较
     * @param e1
     * @param e2
     * @return 返回值等于 0,代表e1和e2相等;返回值大于0,代表e1大于e2;返回值小于0,代表e1小于e2.
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2); // 强制转换 比较
    }
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }

    public void preorder(Visitor<E> visitor) {
        preorder(root, visitor);
    }

    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void inorder(Visitor<E> visitor) {
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        inorder(node.left, visitor);
        visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    public void postorder(Visitor<E> visitor) {
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    public void levelOrder(Visitor<E> visitor) {
        if (root == null || visitor == null) return;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            //System.out.println(node.element);
            visitor.visit(node.element);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 翻转二叉树:将所有节点的左右子树都交换
     * 
     */
    public void invertBinaryTree() {

    }

    /**
     * 判断一棵树是否完全二叉树
     * 如果树为空,返回 false
     * 如果树不为空,开始层序遍历二叉树(用队列)
     *   如果 node.left!=null && node.right!=null, 将 node.left、node.right按顺序入队;
     *   如果 node.left==null && node.right!=null, 返回 false;
     *   如果 node.left!=null && node.right==null 或者 node.left==null && node.right==null,
     *   那么后面遍历的节点应该都为叶子节点,才是二叉树, 返回 true;
     *   否则返回 false.
     * @return
     */
    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) return false;

            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else { // 后面遍历的节点都是叶子节点
                leaf = true;
            }
        }

        return true;
    }

    /**
     * 二叉树的高度
     * @return
     */
    public int height() {
        if (root == null) return 0;

        int height = 0; // 树的高度
        int levelSize = 1; // 存储着每一层的元素数量

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) { // 意味着即将访问下一层
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }
    public int height2() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static interface  Visitor<E> {
        void visit(E element);
    }

    /**
     * 节点
     * @param <E>
     */
    private  static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;
        public  Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
