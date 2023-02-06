package com.fengshuoliu.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree
 */
public class InvertTree {
//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return root;
//        // 前序遍历方式...
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//
//        invertTree(root.left);
//        invertTree(root.right);
//
//        return root;
//    }
//
//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return root;
//        // 后序遍历方式...
//        invertTree(root.left);
//        invertTree(root.right);
//
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//
//        return root;
//    }
//
//    public TreeNode invertTree(TreeNode root) {
//        if (root == null) return root;
//        // 中序遍历方式...
//        invertTree(root.left);
//
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//
//        invertTree(root.left); // 注意这里,交换后
//
//        return root;
//    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        // 层序遍历方式...
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
