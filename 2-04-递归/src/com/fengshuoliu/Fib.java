package com.fengshuoliu;

public class Fib {

    /**
     * 递归实现 fib 出现特别多的重复计算
     * ◼ 根据递推式T n =T n−1 +T(n−2)+O(1)，可得知时间复杂度:O 2n
     * ◼ 空间复杂度:O(n)
     * 递归调用的空间复杂度 = 递归深度 * 每次调用所需的辅助空间
     * @param n
     * @return
     */
    int fib0(int n) {
        if (n <= 2) return 1;
        return fib0(n - 1) + fib0(n - 2);
    }

    /**
     * fib优化1 – 记忆化
     * ◼ 用数组存放计算过的结果，避免重复计算
     * ◼ 时间复杂度:O(n)，空间复杂度:O(n)
     * @param n
     * @return
     */
    int fib1(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fib1(n, array);
    }
    int fib1(int n, int[] array) {
        if (array[n] == 0) {
            array[n] = fib1(n - 1, array) + fib1(n - 2, array);
        }
        return array[n];
    }

    /**
     * fib优化2
     * ◼ 去除递归调用
     * ◼ 时间复杂度:O(n)，空间复杂度:O(n)
     * ◼ 这是一种“自底向上”的计算过程
     * @param n
     * @return
     */
    int fib2(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * fib优化3
     * ◼由于每次运算只需要用到数组中的 2 个元素，所以可以使用滚动数组来优化
     * ◼ 时间复杂度:O(n)，空间复杂度:O(1)
     * @param n
     * @return
     */
    int fib3(int n) {
        if (n <= 2) return n;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2];
        }
        return array[n % 2];
    }

    /**
     * fib优化4 – 位运算取代模运算
     * ◼ 乘、除、模运算效率较低，建议用其他方式取代
     * @param n
     * @return
     */
    int fib4(int n) {
        if (n <= 2) return n;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[n & 1];
    }

    /**
     * fib优化5
     * ◼ 时间复杂度:O(n)，空间复杂度:O(1)
     * @param n
     * @return
     */
    int fib5(int n) {
        if (n <= 2) return 1;
        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
