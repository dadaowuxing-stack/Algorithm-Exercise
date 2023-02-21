package com.fengshuoliu.sort;

/**
 * 插入排序-优化:思路是将[交换]转为[挪动]
 * ① 先将待插入的元素备份
 * ② 头部有序数据中比待插入元素大的,都朝尾部方向挪动 1 个位置
 * ③ 将待插入元素放到最终合适位置
 * @param <T>
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T>{
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int current = begin;
            T v = array[current];
            while (current > 0 && cmp(v, array[current - 1]) < 0) {
                array[current] = array[current - 1];
                current--;
            }
            array[current] = v;
        }
    }
}
