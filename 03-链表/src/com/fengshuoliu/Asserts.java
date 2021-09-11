package com.fengshuoliu;

public class Asserts {
    public static void test(Boolean value) {
        try {
            if (!value)  throw new Exception("测试未通过");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
