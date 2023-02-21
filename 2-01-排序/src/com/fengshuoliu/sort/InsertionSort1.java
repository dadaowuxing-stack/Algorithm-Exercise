package com.fengshuoliu.sort;

/**
 * 插入排序(Insertion Sort)类似于扑克牌的排序
 * ① 在执行过程中,插入排序会将序列分为 2 部分
 * 头部是已经排好序的,尾部是待排序的
 * ② 从头开始扫描每个元素
 * 每当扫描到一个元素,就将它插入到头部合适的位置,使得头部数据依然保持有序
 *
 * 逆序对(Inversion)
 * 插入排序的时间复杂度与逆序对的数量成正比关系
 *
 * 优化:思路是将[交换]转为[挪动]
 * ① 先将待插入的元素备份
 * ② 头部有序数据中比待插入元素大的,都朝尾部方向挪动 1 个位置
 * ③ 将待插入元素放到最终合适位置
 *
 * @param <T>
 */
public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int current = begin;
            while (current > 0 && cmp(current, current - 1) < 0) {
                swap(current, current - 1);
                current--;
            }
        }
    }
}
