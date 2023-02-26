package com.fengshuoliu.sort;

/**
 * 快速排序(Quick Sort)
 * 执行流程:
 * ① 从序列中选择一个轴点元素(pivot)
 * 假设每次选择 0 位置的元素为轴点元素
 * ② 利用 pivot 将序列分割成 2 个子序列
 * 将小于 pivot 的元素放在 pivot 前面(左侧)
 * 将大于 pivot 的元素放在 pivot 后面(右侧)
 * 等于 pivot 的元素放在哪边都可以
 * ③ 对子序列进行①②操作
 * 直到不能再分割(子序列中只剩下 1 个元素)
 * <p>
 * 快速排序的本质:逐渐将每个元素都转换成轴点元素
 * <p>
 * 轴点构造:
 *
 * @param <T>
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对 [begin, end) 范围的元素进行快速排序
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        // 确定轴点位置
        int mid = pivotIndex(begin, end);
        // 对子序列进行快速排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    /**
     * 构造出 [begin, end) 范围的轴点元素
     *
     * @param begin
     * @param end
     * @return 轴点元素的最终位置
     */
    private int pivotIndex(int begin, int end) {
        // 随机选择一个元素作为轴点元素跟 begin 位置进行交换
        swap(begin, begin + (int)Math.random() * (end - begin));
        // 备份 begin 位置的元素
        T pivot = array[begin];
        // end 指向最后一个元素
        end--;
        while (begin < end) {
            // 从右往左
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) { // 右边元素 > 轴点元素
                    end--;
                } else { // 右边元素 <= 轴点元素
                    array[begin++] = array[end];
                    break;
                }
            }
            // 从左往右
            while (begin < end) {
                if (cmp(pivot, array[begin]) > 0) { // 左边元素 < 轴点元素
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        // 将轴点元素放入最终位置
        array[begin] = pivot;
        // 返回轴点元素的位置
        return begin; // begin == end
    }
}
