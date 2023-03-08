package com.fengshuoliu;

public class Queens3 {

    public static void main(String[] args) {
        // 只针对 8 皇后
        new Queens3().place8Queens();
    }

    /**
     * 数组索引是行号,数组元素是列号
     */
    int[] queens;
    /**
     * 标记着某一列是否有皇后
     */
    byte cols;
    /**
     * 标记着某一对角线是否有皇后了(左上角->右下角, left top -> right bottom)
     */
    short leftTop;
    /**
     * 标记着某一对角线是否有皇后了(右上角->左下角, right top -> left bottom)
     */
    short rightTop;
    /**
     * 一共有多少种摆法
     */
    int ways;

    void place8Queens() {
        queens = new int[8];
        place(0);
        System.out.println("8皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第 row 行开始摆放皇后
     *
     * @param row
     */
    void place(int row) {
        if (row == 8) {
            ways++;
            show();
            return;
        }

        for (int col = 0; col < 8; col++) {
            int cv = 1 << col;
            if ((cols & cv) != 0) continue;
            int lv = 1 << (row - col + 8 - 1);
            if ((leftTop & lv) != 0 ) continue;
            int rv = 1 << (row + col);
            if ((rightTop & rv) != 0) continue;

            queens[row] = col;
            cols |= cv;
            leftTop |= lv;
            rightTop |= rv;

            place(row + 1);
            /**
             * 回溯重置
             * 这里为什么要重置?
             */
            cols &= ~cv;
            leftTop &= ~lv;
            rightTop &= ~rv;
        }
    }

    /**
     * 判断第 row 行第 col 列是否可以摆皇后
     *
     * @param row
     * @param col
     * @return
     */
    boolean isValid(int row, int col) {

        return true;
    }

    void show() {
        for (int row = 0; row < queens.length; row++) {
            for (int col = 0; col < queens.length; col++) {
                if (queens[row] == col) {
                    System.out.print("1  ");
                } else {
                    System.out.print("0  ");
                }
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}
