package com.fengshuoliu.tree;

import com.fengshuoliu.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public  void clear() {
        root = null;
        size = 0;
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
    public void preorder(BST.Visitor<E> visitor) {

        preorder(root, visitor);
    }

    private void preorder(Node<E> node, BST.Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void inorder(BST.Visitor<E> visitor) {
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, BST.Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        inorder(node.left, visitor);
        visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    public void postorder(BST.Visitor<E> visitor) {
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, BST.Visitor<E> visitor) {
        if (node == null || visitor == null) return;

        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    public void levelOrder(BST.Visitor<E> visitor) {
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
     * 前驱节点(predecessor):中序遍历时的前一个节点
     *   如果是二叉搜索树,前驱节点就是前一个比它小的节点
     * node.left != null
     *   predecessor = node.left.right.right.right...
     *   终止条件:right为null
     * node.left == null && node.parent != null
     *   predecessor = node.parent.parent.parent...
     *   终止条件:node在 parent 的右子树中
     * node.left == null && node.parent == null
     *   那就没有前驱节点
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        // 前驱节点在左子树当中(left.right.right.right...)
        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        return node.parent;
    }

    /**
     * 后继节点(successor):中序遍历时的后一个节点
     * node.right != null
     *   successor = node.right.left.left.left...
     *   终止条件:left 为 null
     * node.right == null && node.parent != null
     *   successor = node.parent.parent.parent...
     *   终止条件:node 在 parent 的左子树中
     * node.right == null && node.parent == null
     *   那就没有后继节点
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;

        // 后继节点在左子树当中(right.left.left.left...)
        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
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
    protected  static class Node<E> {
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
}
