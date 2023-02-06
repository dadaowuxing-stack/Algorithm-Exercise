package com.fengshuoliu;

import com.fengshuoliu.printer.BinaryTrees;
import com.fengshuoliu.tree.AVLTree;
import com.fengshuoliu.tree.Person;

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
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        AVLTree<Integer> avl = new AVLTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            avl.add(data[i]);
        }

        BinaryTrees.println(avl);
    }

    public static void main(String[] args) {

        test1();
    }
}
