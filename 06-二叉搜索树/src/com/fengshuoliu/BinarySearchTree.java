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
        root = null;
        size = 0;
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

    /**
     * 删除节点
     * 1.删除叶子节点 直接删除
     * node == node.parent.left
     *   node.parent.left = null
     *
     * node == node.parent.right
     *   node.parent.right = null
     *
     * node.parent == null
     *   root = null
     *
     * 2.删除度为 1 的节点: 用子节点替代原节点的位置
     * child 是 node.left 或者 child 是 node.right
     * 用 child 替代 node 的位置
     *   如果被删除的 node 是左节点
     *   child.parent = node.parent
     *   node.parent.left = child
     *   如果被删除的 node 是右节点
     *   child.parent = node.parent
     *   node.parent.right = child
     *   如果被删除 node 是根节点
     *   root = child
     *   child.parent = null
     *
     * 3.删除度为 2 的节点
     * 先用前驱或者后继节点的值覆盖原节点的值
     * 然后删除响应的前驱或者后继节点
     *
     * 如果一个节点的度为 2,那么它的前驱、后继节点的度只可能是 1 和 0
     *
     * @param element
     */
    public void remove(E element) {
        
        remove(node(element));
    }

    public boolean contains(E element) {

        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<E> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }
        // 删除node节点(node的度必然是 1 或者 0)
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // node是度为 1 的节点
            // 更改 parent
            replacement.parent = node.parent;
            // 更改parent 的 left、right 的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;
        } else { // node是叶子几点,但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }
        }
    }

    /**
     * 根据元素找到对应的节点
     * @param element
     * @return
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
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
    private Node<E> predecessor(Node<E> node) {
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
    private Node<E> successor(Node<E> node) {
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
     * 1.
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
    public boolean isComplete2() {
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
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }

        return true;
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

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                // node.left == null && node.right != null
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                // node.left == null && node.right == null
                // node.left != null && node.right == null
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
