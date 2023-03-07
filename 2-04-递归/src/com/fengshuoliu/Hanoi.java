package com.fengshuoliu;

/**
 * ◼其实分 2 种情况讨论即可
 * n
 * 当 n == 1时，直接将盘子从 A 移动到 C
 * 当 n > 1时，可以拆分成3大步骤
 * ① 将n–1个盘子从A移动到B
 * ② 将编号为n的盘子从A移动到C
 * ③ 将n–1个盘子从B移动到C
 * ✓步骤 1 3 明显是个递归调用
 */
public class Hanoi {

    public static void main(String[] args) {
        new Hanoi().hanoi(3, "A", "B", "C");
    }
    /**
     * 将 n 个碟子从 p1 挪动到 p3
     * @param n
     * @param p1
     * @param p2 中间的柱子
     * @param p3
     */
    void hanoi(int n, String p1, String p2, String p3) {
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        hanoi(n - 1, p1, p3, p2);
        move(n, p1, p3);
        hanoi(n - 1, p2, p1, p3);
    }

    void move(int no, String from, String to) {
        System.out.println("将" + no + "号盘子从" + from + "移动到" + to);
    }
}
