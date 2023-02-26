package com.fengshuoliu.sort;

/**
 * 归并排序(Merge Sort)
 * 执行流程:
 * ① 不断地将当前序列平均分割成 2 个子序列
 * 直到不能再分割(序列中只剩 1 个元素)
 * ② 不断地将 2 个子序列合并成有序序列
 * 直到最终只剩下 1 个有序序列
 *
 * @param <T>
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    // T(n) = T(n/2) + T(n/2) + O(n)

    /**
     * 对 [begin, end)范围的数据进行归并排序
     * end - begin 为元素数量
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;

        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将 [begin, mid) 和 [mid, end) 范围的序列合并成一个有序序列
     *
     * @param begin
     * @param mid
     * @param end
     */
    private void merge(int begin, int mid, int end) {
        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        int ai = begin;

        // 备份左边数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        // 如果左边还没有结束
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = leftArray[li++];
            }
        }
    }
}
