package com.fengshuoliu.sort.cmp;

import com.fengshuoliu.sort.Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 希尔排序(Shell Sort): 也被成为递减增量排序(Diminishing Increment Sort)
 * 希尔排序把序列看作一个矩阵,分层 m 列,逐列进行排序
 * m 从某个整数逐渐减少为1
 * 当 m 为 1 时,整个序列将完全有序
 *
 * 每排完一次,逆序对会减少
 *
 * 矩阵的列数取决于步长序列(step sequence)
 * 希尔排序底层一般使用插入排序对每一列进行排序
 * @param <T>
 */
public class ShellSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        List<Integer> stepSequence = sedgewickStepSequence();//shellStepSequence();
        System.out.println(stepSequence);
        for (Integer step: stepSequence) {
            sort(step);
        }
    }

    /**
     * 分成 step 列进行排序
     * @param step
     */
    private void sort(int step) {
        // col:第几列, column
        for (int col = 0; col < step; col++) { // 对第 col列进行排序
            // 对[0, array.length)范围的元素进行插入排序
            /**
             * 假设元素在第 col 列、第 row 行,步长(总列数)是 step
             * 那么这个元素在数组中的索引是 col + row * step
             * 比如 9 在排序前是第 2 列、第 0 行,那么它排序前的索引是 2 + 0 * 5 = 2
             * 比如 4 在排序前是第 2 列、第 1 行,那么它排序前的索引是 2 + 1 * 5 = 2
             */
            for (int begin = col  + step; begin < array.length; begin += step) {
                int current = begin;
                while (current > col && cmp(current, current - step) < 0) {
                    swap(current, current - step);
                    current -= step;
                }
            }
        }
    }

    private  List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    private List<Integer> sedgewickStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        int k = 0, step = 0;
        while (true) {
            if (k % 2 == 0) {
                int pow = (int)Math.pow(2, k >> 1);
                step = 1 + 9 * (pow * pow - pow);
            } else {
                int pow1 = (int)Math.pow(2, (k - 1) >> 1);
                int pow2 = (int)Math.pow(2, (k + 1) >> 1);
                step = 1 + 8 * pow1 * pow2 - 6 * pow2;
            }
            if (step >= array.length) break;
            stepSequence.add(0, step);
            k++;
        }
        return stepSequence;
    }
}
