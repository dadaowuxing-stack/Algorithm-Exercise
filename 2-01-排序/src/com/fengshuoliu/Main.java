package com.fengshuoliu;

import com.fengshuoliu.sort.*;
import com.fengshuoliu.tools.Asserts;
import com.fengshuoliu.tools.Integers;
import com.fengshuoliu.tools.Times;

import java.util.Arrays;

public class Main {
    /**
     * 1.冒泡排序(Bubble Sort以升序为例)
     * ①.从头开始比较每一对相邻元素,如果第1个比第2个大,就交换它们的位置
     * 执行完一轮后,最末尾那个元素就是最大的元素
     * ②.忽略 ① 中曾经找到的最大元素,重复执行步骤①,直到全部元素有序
     * 2.选择排序(Selection Sort)
     * ①.从序列中找出最大的那个元素,然后与最末尾的元素交换位置
     * 执行完一轮后,最末尾的那个元素就是最大元素
     * ②.忽略①中曾经找到的最大元素,重复执行步骤①
     * <p>
     * 选择排序的交换次数要远远少于冒泡排序,平均性能优于冒泡排序
     * 3.堆排序(Heap sort):堆排序可以认为是对选择排序的一种优化
     * ①.对序列进行原地建堆(heapify)
     * ②.重复执行以下操作,直到堆的元素数量为1
     * 交换堆顶元素与尾元素
     * 堆的元素数量减1
     * 对 0 位置进行 1 次 siftDown 操作
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] array = Integers.random(10000, 1, 20000);

        testSort(array, new BubbleSort1(), new BubbleSort2(), new HeapSort(), new SelectionSort(), new BubbleSort3());
//        Times.test("HeapSort", () -> {
//            new HeapSort().sort(array);
//        });
//        Times.test("SelectionSort", () -> {
//            new SelectionSort().sort(Integers.copy(array));
//        });
//        Times.test("BubbleSort3", () -> {
//            new BubbleSort3().sort(Integers.copy(array));
//        });

//        Integer[] array1 = Integers.tailAscOrder(1, 10000, 2000);
//        Integer[] array2 = Integers.copy(array1);
//        Integer[] array3 = Integers.copy(array1);
//        Times.test("bubbleSort1", () -> {
//            bubbleSort1(array1);
//        });
//        Times.test("bubbleSort2", () -> {
//            bubbleSort2(array2);
//        });
//        Times.test("bubbleSort3", () -> {
//            bubbleSort3(array3);
//        });
    }

    static void testSort(Integer[] array, Sort... sorts) {
        for (Sort sort : sorts) {
            sort.sort(Integers.copy(array));
        }

        Arrays.sort(sorts);

        for (Sort sort: sorts) {
            System.out.println(sort);
        }
    }
}