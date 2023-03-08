package com.fengshuoliu;

public class Queens2 {

    public static void main(String[] args) {
        new Queens2().placeQueens(4);
    }

    /**
     * 数组索引是行号,数组元素是列号
     */
    int[] queens;
    /**
     * 标记着某一列是否有皇后
     */
    boolean[] cols;
    /**
     * 标记着某一对角线是否有皇后了(左上角->右下角, left top -> right bottom)
     */
    boolean[] leftTop;
    /**
     * 标记着某一对角线是否有皇后了(右上角->左下角, right top -> left bottom)
     */
    boolean[] rightTop;
    /**
     * 一共有多少种摆法
     */
    int ways;

    void placeQueens(int n) {
        if (n < 1) return;
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /**
     * 从第 row 行开始摆放皇后
     *
     * @param row
     */
    void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }
        /**
         * 八皇后优化 – 对角线
         * ◼右上角 -> 左下角的对角线索引:row + col
         * ◼左上角 -> 右下角的对角线索引:row – col + n - 1
         */
        for (int col = 0; col < cols.length; col++) {
            if (cols[col]) continue;
            int ltIndex = row - col + cols.length - 1;
            if (leftTop[ltIndex]) continue;
            int rtIndex = row + col;
            if (rightTop[rtIndex]) continue;

            queens[row] = col;

            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
            place(row + 1);
            /**
             * 回溯重置
             * 这里为什么要重置?
             */
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
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
