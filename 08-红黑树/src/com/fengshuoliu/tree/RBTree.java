package com.fengshuoliu.tree;

import java.util.Comparator;

/**
 * 红黑树(Red Black Tree)
 * 红黑树也是一种自平衡的二叉树
 * 以前也叫做平衡二叉B树(Symmetric Binary B-tree)
 * 红黑树必须满足以下 5 条性质:
 * 1.节点是 RED 或者 BLACK
 * 2.根节点是 BLACK
 * 3.叶子节点(外部节点,空节点)都是 BLACK
 * 4.RED 节点的子节点都是 BLACK
 *   RED 节点的 parent 都是 BLACK
 *   从根节点到叶子节点的所有路径上不能有 2 个连续的 RED 节点
 * 5.从任一节点到叶子节点的所有路径都包含相同数目的 BLACK 节点
 * 这五条性质,可以保证红黑树等价于4阶B树
 * 相比AVL树,红黑树的平衡标准比较宽松:没有一条路径会大于其他路径的2倍
 * 是一种弱平衡、黑高度平衡
 * 红黑树的最大高度 2*log2(n+1),依然是 O(logn)级别
 * 平均时间复杂度:搜索O(logn); 添加O(logn),O(1)次的旋转操作;删除O(logn),O(1)次的旋转操作.
 * @param <E>
 */
public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterRemove(Node<E> node) {
        // 如果删除的节点是红色 或者 用以取代 node 的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }
        Node<E> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;
        // 删除的是黑色叶子节点[下溢]
        // 判断被删除的node是左还是右
        boolean left = parent.left == null;
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边,兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }
            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点,父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色节点,向兄弟节点借元素
                if (isBlack(sibling.right)) { // 兄弟节点右边是黑色,兄弟要先旋转
                    rotateLeft(sibling);
                    // 更换兄弟
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        } else { // 被删除的节点在右边,兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }
            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点,父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色节点,向兄弟节点借元素
                if (isBlack(sibling.left)) { // 兄弟节点左边是黑色,兄弟要先旋转
                    rotateLeft(sibling);
                    // 更换兄弟
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

//    @Override
//    protected void afterRemove(Node<E> node, Node<E> replacement) {
//        // 如果删除的节点是红色
//        if (isRed(node)) return;
//        // 用以取代 node 的子节点是红色
//        if (isRed(replacement)) {
//            black(replacement);
//            return;
//        }
//        Node<E> parent = node.parent;
//        // 删除的是根节点
//        if (parent == null) return;
//        // 删除的是黑色叶子节点[下溢]
//        // 判断被删除的node是左还是右
//        boolean left = parent.left == null;
//        Node<E> sibling = left ? parent.right : parent.left;
//        if (left) { // 被删除的节点在左边,兄弟节点在右边
//            if (isRed(sibling)) { // 兄弟节点是红色
//                black(sibling);
//                red(parent);
//                rotateLeft(parent);
//                sibling = parent.right;
//            }
//            // 兄弟节点必然是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟节点没有1个红色子节点,父节点要向下跟兄弟节点合并
//                boolean parentBlack = isBlack(parent);
//                black(parent);
//                red(sibling);
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//            } else { // 兄弟节点至少有1个红色节点,向兄弟节点借元素
//                if (isBlack(sibling.right)) { // 兄弟节点右边是黑色,兄弟要先旋转
//                    rotateLeft(sibling);
//                    // 更换兄弟
//                    sibling = parent.right;
//                }
//                color(sibling, colorOf(parent));
//                black(sibling.left);
//                black(parent);
//                rotateRight(parent);
//            }
//        } else { // 被删除的节点在右边,兄弟节点在左边
//            if (isRed(sibling)) { // 兄弟节点是红色
//                black(sibling);
//                red(parent);
//                rotateRight(parent);
//                sibling = parent.left;
//            }
//            // 兄弟节点必然是黑色
//            if (isBlack(sibling.left) && isBlack(sibling.right)) {
//                // 兄弟节点没有1个红色子节点,父节点要向下跟兄弟节点合并
//                boolean parentBlack = isBlack(parent);
//                black(parent);
//                red(sibling);
//                if (parentBlack) {
//                    afterRemove(parent, null);
//                }
//            } else { // 兄弟节点至少有1个红色节点,向兄弟节点借元素
//                if (isBlack(sibling.left)) { // 兄弟节点左边是黑色,兄弟要先旋转
//                    rotateLeft(sibling);
//                    // 更换兄弟
//                    sibling = parent.left;
//                }
//                color(sibling, colorOf(parent));
//                black(sibling.left);
//                black(parent);
//                rotateRight(parent);
//            }
//        }
//    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        // 添加时根节点
        if (parent == null) {
            black(node);
            return;
        }
        // 如果父节点是黑色,直接返回
        if (isBlack(parent)) return;
        // uncle 节点
        Node<E> uncle = parent.sibling();
        // 祖父节点
        Node<E> grand = red(parent.parent);
        // uncle 节点是红色[B树节点上溢]
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            afterAdd(grand);
            return;
        }

        // uncle 节点不是红色[不是上溢的情况]
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }
    }
}
