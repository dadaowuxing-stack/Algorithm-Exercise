package com.fengshuoliu;

import com.fengshuoliu.tools.Asserts;
import com.fengshuoliu.tools.Times;

import java.util.TreeMap;

/**
 * 跳表(SkipList)
 * ◼ 跳表，又叫做跳跃表、跳跃列表，在有序链表的基础上增加了“跳跃”的功能
 * 由William Pugh于1990年发布，设计的初衷是为了取代平衡树(比如红黑树)
 * ◼Redis中 的 SortedSet、LevelDB 中的 MemTable 都用到了跳表
 * Redis、LevelDB 都是著名的 Key-Value 数据库
 * ◼ 对比平衡树
 * 跳表的实现和维护会更加简单
 * 跳表的搜索、删除、添加的平均时间复杂度是 O(logn)
 */
public class Main {
    public static void main(String[] args) {
//		SkipList<Integer, Integer> list = new SkipList<>();
//		test(list, 30, 10);

        time();
    }

    private static void time() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
//		SkipList<Integer, Integer> list = new SkipList<>();
        int count = 100_0000;
        int delta = 10;

//		Times.test("SkipList", () -> {
//			test(list, count, delta);
//		});

        Times.test("TreeMap", () -> {
            test(map, count, delta);
        });
    }

    private static void test(SkipList<Integer, Integer> list, int count, int delta) {
        for (int i = 0; i < count; i++) {
            list.put(i, i + delta);
        }
//		System.out.println(list);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.get(i) == i + delta);
        }
        Asserts.test(list.size() == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(list.remove(i) == i + delta);
        }
        Asserts.test(list.size() == 0);
    }

    private static void test(TreeMap<Integer, Integer> map, int count, int delta) {
        for (int i = 0; i < count; i++) {
            map.put(i, i + delta);
        }
        for (int i = 0; i < count; i++) {
            Asserts.test(map.get(i) == i + delta);
        }
        Asserts.test(map.size() == count);
        for (int i = 0; i < count; i++) {
            Asserts.test(map.remove(i) == i + delta);
        }
        Asserts.test(map.size() == 0);
    }
}