package com.fengshuoliu;

import com.fengshuoliu.file.FileInfo;
import com.fengshuoliu.file.Files;
import com.fengshuoliu.map.Map;
import com.fengshuoliu.map.Map.Visitor;
import com.fengshuoliu.map.TreeMap;
import com.fengshuoliu.set.Set;
import com.fengshuoliu.set.TreeSet;

public class Main {

    static void test1() {
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("c", 2);
        treeMap.put("a", 5);
        treeMap.put("b", 6);
        treeMap.put("a", 8);

        treeMap.traversal(new Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }

    static void test2() {
        FileInfo fileInfo = Files.read("", new String[]{"java"});
        System.out.println("文件数量:" + fileInfo.getFiles());
        System.out.println("代码行数:" + fileInfo.getLines());
        String[] words = fileInfo.words();
        System.out.println("单词数量:" + words.length);
        Map<String, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < words.length; i++) {
            Integer count = treeMap.get(words[i]);
            count = (count == null) ? 1 : (count + 1);
            treeMap.put(words[i], count);
        }
        treeMap.traversal(new Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
    static void test3() {
        Set<String> set = new TreeSet<>();
        set.add("c");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("a");

        set.traversal(new Set.Visitor<String>() {
            @Override
            public boolean visit(String element) {
                System.out.println(element);
                return false;
            }
        });
    }
    public static void main(String[] args) {
        test3();
    }
}