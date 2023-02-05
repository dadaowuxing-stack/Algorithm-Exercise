package com.fengshuoliu;

import com.fengshuoliu.printer.BinaryTreeInfo;

import java.util.Comparator;

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
        return ((Node<E>)node).element;
    }

    /**
     * 维护节点
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
    }
}
