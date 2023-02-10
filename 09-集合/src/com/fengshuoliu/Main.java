package com.fengshuoliu;

import com.fengshuoliu.Times;
import com.fengshuoliu.Times.Task;
import com.fengshuoliu.file.FileInfo;
import com.fengshuoliu.file.Files;
import com.fengshuoliu.set.ListSet;
import com.fengshuoliu.set.Set;
import com.fengshuoliu.set.Set.Visitor;
import com.fengshuoliu.set.TreeSet;

public class Main {
    static void test1() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(11);
        listSet.add(11);
        listSet.add(12);
        listSet.add(10);

        listSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    static void test2() {
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(11);
        treeSet.add(10);
        treeSet.add(11);
        treeSet.add(7);
        treeSet.add(9);

        treeSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    static void testSet(Set<String> set, String[] words) {
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.contains(words[i]);
        }
        for (int i = 0; i < words.length; i++) {
            set.remove(words[i]);
        }
    }

    static void test3() {
        FileInfo fileInfo = Files.read("/Users/liufengshuo/Desktop", new String[]{"java"});
        System.out.println("文件数量:" + fileInfo.getFiles());
        System.out.println("代码行数:" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量:" + fileInfo.words().length);
//        Set<String> listSet = new ListSet<>();
//        for (int i = 0; i < words.length; i++) {
//            listSet.add(words[i]);
//        }
//        System.out.println(listSet.size());
        Times.test("ListSet", new Task() {
            @Override
            public void execute() {
                testSet(new ListSet<>(), words);
            }
        });

        Times.test("TreeSet", new Task() {
            @Override
            public void execute() {
                testSet(new TreeSet<>(), words);
            }
        });
    }

    public static void main(String[] args) {
        test3();
    }
}
