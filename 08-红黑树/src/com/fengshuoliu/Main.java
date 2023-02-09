package com.fengshuoliu;

import com.fengshuoliu.printer.BinaryTrees;
import com.fengshuoliu.tree.AVLTree;
import com.fengshuoliu.tree.Person;
import com.fengshuoliu.tree.RBTree;

import java.util.Comparator;

public class Main {

    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }

    static void test1() {
        Integer data[] = new Integer[] {
                85, 19, 69, 3, 7, 99, 95, 2, 1, 70, 44, 58, 11, 21, 14, 93, 57, 4, 56
        };
        AVLTree<Integer> avl = new AVLTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);
    }
    static void test2() {
        Integer data[] = new Integer[] {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };
        RBTree<Integer> rbt = new RBTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            rbt.add(data[i]);
        }

        BinaryTrees.println(rbt);
    }
    public static void main(String[] args) {

        test2();
    }
}
