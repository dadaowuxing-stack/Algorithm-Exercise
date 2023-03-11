package com.fengshuoliu;

/**
 * 0-1背包
 * 练习6 – 0-1背包
 * ◼ 有 n 件物品和一个最大承重为 W 的背包，每件物品的重量是 𝑤i 、价值是 𝑣i
 * 在保证总重量不超过 W 的前提下，选择某些物品装入背包，背包的最大总价值是多少?
 * 注意:每个物品只有 1 件，也就是每个物品只能选择 0 件或者 1 件
 *
 * 0-1背包 – 恰好装满
 * ◼有 n 件物品和一个最大承重为 W 的背包，每件物品的重量是 𝑤i、价值是 𝑣i
 * 在保证总重量恰好等于 W 的前提下，选择某些物品装入背包，背包的最大总价值是多少?
 * 注意:每个物品只有 1 件，也就是每个物品只能选择 0 件或者 1 件
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 10;
        System.out.println(maxValueExactly(values, weights, capacity));
    }

    /**
     * 0-1背包 – 恰好装满
     * ◼有 n 件物品和一个最大承重为 W 的背包，每件物品的重量是 𝑤i、价值是 𝑣i
     * 在保证总重量恰好等于 W 的前提下，选择某些物品装入背包，背包的最大总价值是多少?
     * 注意:每个物品只有 1 件，也就是每个物品只能选择 0 件或者 1 件
     * @param values
     * @param weights
     * @param capacity
     * @return 如果返回-1,代表没法刚好凑到 capacity 这个容量
     */
    static int maxValueExactly(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int j = 1; j <= capacity; j++) {
            dp[j] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }

    static int maxValue3(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }

    static int maxValue2(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= 1; j--) {
                if (j < weights[i - 1]) continue;
                dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
            }
        }
        return dp[capacity];
    }

    static int maxValue1(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) return 0;
        if (weights == null || weights.length == 0) return 0;
        if (values.length != weights.length || capacity <= 0) return 0;
        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return dp[values.length][capacity];
    }
}
