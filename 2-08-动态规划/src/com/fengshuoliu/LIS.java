package com.fengshuoliu;

/**
 * 最长上升子序列(LIS)
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * ◼ 给定一个无序的整数序列，求出它最长上升子序列的长度(要求严格上升)
 * 比如 [10, 2, 2, 5, 1, 7, 101, 18] 的最长上升子序列是 [2, 5, 7, 101]、[2, 5, 7, 18]，长度是 4
 */
public class LIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {10, 2, 2, 5, 1, 7,101, 18}));
    }

    /**
     * 二分搜索
     * 牌顶
     * ◼ 把每个数字看做是一张扑克牌，从左到右按顺序处理每一个扑克牌
     * 将它压在(从左边数过来)第一个牌顶 ≥ 它的牌堆上面
     * 如果找不到牌顶 ≥ 它的牌堆，就在最右边新建一个牌堆，将它放入这个新牌堆中
     * ◼ 当处理完所有牌，最终牌堆的数量就是最长上升子序列的长度
     *
     * 最长上升子序列 – 二分搜索 – 思路
     * ◼思路(假设数组是 nums，也就是最初的牌数组)
     * top[i] 是第 i 个牌堆的牌顶，len 是牌堆的数量，初始值为 0
     * 遍历每一张牌 num
     * ✓利用二分搜索找出 num 最终要放入的牌堆位置 index
     * ✓num 作为第 index 个牌堆的牌顶，top[index] = num
     * ✓如果 index 等于 len，相当于新建一个牌堆，牌堆数量 +1，也就是 len++
     */
    static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 牌堆的数量
        int len = 0;
        // 牌顶数组
        int[] top = new int[nums.length];
        // 遍历所有的牌
        for (int num : nums) {
            int begin = 0;
            int end = len;
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }
            // 覆盖牌顶
            top[begin] = num;
            // 检查是否要新建一个牌堆
            if (begin == len) len++;
        }
        return len;
    }

    /**
     * 牌顶
     * ◼ 把每个数字看做是一张扑克牌，从左到右按顺序处理每一个扑克牌
     * 将它压在(从左边数过来)第一个牌顶 ≥ 它的牌堆上面
     * 如果找不到牌顶 ≥ 它的牌堆，就在最右边新建一个牌堆，将它放入这个新牌堆中
     * ◼ 当处理完所有牌，最终牌堆的数量就是最长上升子序列的长度
     */
    static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 牌堆的数量
        int len = 0;
        // 牌顶数组
        int[] top = new int[nums.length];
        // 遍历所有的牌
        for (int num : nums) {
            int j = 0;
            while (j < len) {
                // 找到一个>=num的牌顶
                if (top[j] >= num) {
                    top[j] = num;
                    break;
                }
                // 牌顶 < num
                j++;
            }
            if (j == len) { // 新建一个牌堆
                len++;
                top[j] = num;
            }
        }
        return len;
    }

    /**
     * 动态规划
     */
    static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
