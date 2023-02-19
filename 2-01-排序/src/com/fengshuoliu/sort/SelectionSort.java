package com.fengshuoliu.sort;

/**
 * 2.选择排序(Selection Sort)
 * ①.从序列中找出最大的那个元素,然后与最末尾的元素交换位置
 * 执行完一轮后,最末尾的那个元素就是最大元素
 * ②.忽略①中曾经找到的最大元素,重复执行步骤①
 *
 * 选择排序的交换次数要远远少于冒泡排序,平均性能优于冒泡排序
 */
public class SelectionSort extends Sort {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(maxIndex, begin) <= 0) {// 稳定的排序,这里要用等于
                    maxIndex = begin;
                }
            }
            swap(maxIndex, end);
        }
    }
}
