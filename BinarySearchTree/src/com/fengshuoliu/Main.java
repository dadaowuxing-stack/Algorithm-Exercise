package com.fengshuoliu;

import com.fengshuoliu.file.Files;
import com.fengshuoliu.printer.BinaryTrees;
import com.fengshuoliu.BinarySearchTree.Visitor;

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
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
    }

    static void test2() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(new Person(data[i]));
        }

        BinaryTrees.println(bst1);

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        }

        BinaryTrees.println(bst2);
    }

    static void test3() {
        BinarySearchTree<Integer> bst3 = new BinarySearchTree<>();
        for (int i = 0; i < 40; i++) {
            bst3.add((int) (Math.random() * 100));
        }
         BinaryTrees.println(bst3);
        System.out.println(bst3.height());
//        String str = BinaryTrees.printString(bst3);
//        str += "\n";
//        Files.writeToFile("/Users/liufengshuo/1.txt", str);
    }

    static void test4() {
        BinarySearchTree<Person> bst4 = new BinarySearchTree<>();
        bst4.add(new Person(10, "jack"));
        bst4.add(new Person(12, "rose"));
        bst4.add(new Person(6, "jim"));

        bst4.add(new Person(10, "michael"));

        BinaryTrees.println(bst4);
    }

    static void test5() {
        Integer data[] = new Integer[] {
                7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
        };
        BinarySearchTree<Integer> bst5 = new BinarySearchTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            bst5.add(data[i]);
        }

        BinaryTrees.println(bst5);

        //bst5.preorderTraversal();
        //bst5.inorderTraversal();
        //bst5.postorderTraversal();
        //bst5.levelOrderTraversal();

//        bst5.levelOrder(new Visitor<Integer>() {
//            @Override
//            public void visit(Integer element) {
//                System.out.print("_" + element + "_ ");
//            }
//        });
//        bst5.inorder(new Visitor<Integer>() {
//            @Override
//            public void visit(Integer element) {
//                System.out.print("_" + element + "_ ");
//            }
//        });

        System.out.println(bst5.height());
    }

    static void test6() {
        Integer data[] = new Integer[]{
                7, 4, 9, 2, 5, 8, 11, 3, 1, 12
        };
        BinarySearchTree<Integer> bst6 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst6.add(data[i]);
        }

        BinaryTrees.println(bst6);
        System.out.println(bst6.isComplete());
    }
    public static void main(String[] args) {

        test6();
    }
}
