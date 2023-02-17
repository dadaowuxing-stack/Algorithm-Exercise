package com.fengshuoliu;

import com.fengshuoliu.heap.BinaryHeap;
import com.fengshuoliu.printer.BinaryTrees;

import java.util.Comparator;

public class Main {
    static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);

        BinaryTrees.println(heap);
        //heap.remove();
        heap.replace(70);
        BinaryTrees.println(heap);
    }

    static void test2() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data);
        BinaryTrees.println(heap);

        data[0] = 10;
        data[1] = 20;
        BinaryTrees.println(heap);
    }

    static void test3() {
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        BinaryHeap<Integer> heap = new BinaryHeap<>(data, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        BinaryTrees.println(heap);
    }

    static void test4() {

        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 找出最大的前 k 个数
        int k = 5;
        Integer[] data = {88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37, 33, 49};
        for (int i = 0; i < data.length; i++) {
            if (heap.size() < k) { // 前k个数添加到最小顶堆
                heap.add(data[i]); // logk
            } else if (data[i] > heap.get()) { // 如果是第k + 1个数,并且大于堆顶元素
                heap.replace(data[i]); // logk
            }
        }
        // O(nlogk)
        BinaryTrees.println(heap);
    }

    public static void main(String[] args) {
        test4();
    }
}