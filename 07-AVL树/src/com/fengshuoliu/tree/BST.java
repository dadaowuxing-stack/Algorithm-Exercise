package com.fengshuoliu.tree;

import java.util.Comparator;

/**
 * 二叉搜索树
 * 遍历的应用
 * 前序遍历:树状结构展示(注意左右子树顺序)
 * 中序遍历:二叉搜索树的中序遍历按升序或者降序处理
 * 后序遍历:适用于一些先子后父的操作
 * 层序遍历:计算二叉树的高度;判断一棵树是否为完全二叉树
 *
 * @param <E>
 */
public class BST<E> extends BinaryTree<E> {
    private Comparator<E> comparator; // 比较器

    public BST() {
        this(null);
    }

    public BST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);

        // 添加第一个结点
        if (root == null) {
            root = createNode(element, null);//new Node<>(element, null);
            size++;

            afterAdd(root);
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
        Node<E> newNode = createNode(element, parent);//new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterAdd(newNode);
    }

    /**
     * 添加 node 之后的调整
     *
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<E> node) {

    }

    /**
     * 删除节点
     * 1.删除叶子节点 直接删除
     * node == node.parent.left
     * node.parent.left = null
     * <p>
     * node == node.parent.right
     * node.parent.right = null
     * <p>
     * node.parent == null
     * root = null
     * <p>
     * 2.删除度为 1 的节点: 用子节点替代原节点的位置
     * child 是 node.left 或者 child 是 node.right
     * 用 child 替代 node 的位置
     * 如果被删除的 node 是左节点
     * child.parent = node.parent
     * node.parent.left = child
     * 如果被删除的 node 是右节点
     * child.parent = node.parent
     * node.parent.right = child
     * 如果被删除 node 是根节点
     * root = child
     * child.parent = null
     * <p>
     * 3.删除度为 2 的节点
     * 先用前驱或者后继节点的值覆盖原节点的值
     * 然后删除响应的前驱或者后继节点
     * <p>
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
     *
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
     * 比较
     *
     * @param e1
     * @param e2
     * @return 返回值等于 0,代表e1和e2相等;返回值大于0,代表e1大于e2;返回值小于0,代表e1小于e2.
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2); // 强制转换 比较
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
}
