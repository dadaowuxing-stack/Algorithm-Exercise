package com.fengshuoliu.sort;

/**
 * 1.冒泡排序(Bubble Sort以升序为例)
 * ①.从头开始比较每一对相邻元素,如果第1个比第2个大,就交换它们的位置
 * 执行完一轮后,最末尾那个元素就是最大的元素
 * ②.忽略 ① 中曾经找到的最大元素,重复执行步骤①,直到全部元素有序
 */
public class BubbleSort1<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin - 1) < 0) {
                    swap(begin, begin - 1);
                }
            }
        }
    }
}
