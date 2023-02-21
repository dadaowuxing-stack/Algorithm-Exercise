package com.fengshuoliu;

public class BinarySearch {
    /**
     * 查找 v 在有序数组 array 中的位置
     * 思考:如果存在多个重复的值,返回哪一个?
     *
     * @param array
     * @param v
     * @return
     */
    public static int indexOf(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        // 左闭右开[begin, end)
        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int mid = (end + begin) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找 v 在有序数组 array 中待插入位置
     *
     * @param array
     * @param v
     * @return
     */
    public static int search(int[] array, int v) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
