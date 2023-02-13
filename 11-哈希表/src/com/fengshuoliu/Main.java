package com.fengshuoliu;

import java.util.HashMap;
import java.util.Map;

public class Main {

    static void test1() {
        String string = "jack";
        int len = string.length();
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            char c = string.charAt(i);
            //hashCode = hashCode * 31 + c;
            hashCode = (hashCode << 5) - hashCode + c;
        }
        System.out.println(hashCode);
    }

    static void test2() {
        Integer a = 110;
        Float b = 10.6f;
        Long c = 156L;
        Double d = 10.9;
        String e = "rose";

    }

    static void test3() {
        Person p1 = new Person(10, 1.67f, "jack");
        Person p2 = new Person(10, 1.67f, "jack");
        //System.out.println(p1.hashCode());
        //System.out.println(p2.hashCode());

        Map<Object, Object> map = new HashMap<>();
        map.put(p1, "jack");
        map.put(p2, "bcd");
        System.out.println(map.size());
    }
    public static void main(String[] args) {
        test3();
    }
}