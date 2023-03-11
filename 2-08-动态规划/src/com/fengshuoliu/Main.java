package com.fengshuoliu;

/**
 * 动态规划(Dynamic Programming)
 * ◼ 动态规划，简称DP
 * 是求解最优化问题的一种常用策略
 * ◼ 通常的使用套路(一步一步优化)
 * 1 暴力递归(自顶向下，出现了重叠子问题)
 * 2 记忆化搜索(自顶向下)
 * 3 递推(自底向上)
 *
 * 动态规划的常规步骤
 * ◼ 动态规划中的“动态”可以理解为是“会变化的状态”
 * 1 定义状态(状态是原问题、子问题的解)
 * ✓比如定义 dp(i) 的含义
 * 2 设置初始状态(边界)
 * ✓比如设置 dp(0) 的值
 * 3 确定状态转移方程
 * ✓比如确定 dp(i) 和 dp(i – 1) 的关系
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}