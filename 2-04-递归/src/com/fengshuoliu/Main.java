package com.fengshuoliu;

/**
 * 递归:函数(方法)直接或间接调用自身.是一种常用的编程技巧.
 * 基本思想:
 *  拆解问题
 *    把规模大的问题变成规模较小的同类型问题
 *    规模较小的问题又不断变成规模更小的问题
 *    规模小到一定程度可以直接得出它的解
 *  求解
 *    由最小规模问题的解得出较大规模问题的解
 *    由较大规模问题的解不断得出规模更大问题的解
 *    最后得出原来问题的解
 * 递归的使用套路
 * 1 明确函数的功能
 * 先不要去思考里面代码怎么写，首先搞清楚这个函数的干嘛用的，能完成什么功能?
 * 2 明确原问题与子问题的关系
 * 寻找 f(n) 与 f(n – 1) 的关系
 * 3 明确递归基(边界条件)
 * 递归的过程中，子问题的规模在不断减小，当小到一定程度时可以直接得出它的解
 * 寻找递归基，相当于是思考:问题规模小到什么程度可以直接得出解?
 */
public class Main {
    public static void main(String[] args) {
        Fib fib = new Fib();
        int n = 30;
        Times.test("fib0",() -> {
            System.out.println(fib.fib0(n));
        });
        Times.test("fib1",() -> {
            System.out.println(fib.fib1(n));
        });
        Times.test("fib2",() -> {
            System.out.println(fib.fib2(n));
        });
        Times.test("fib3",() -> {
            System.out.println(fib.fib3(n));
        });
        Times.test("fib4",() -> {
            System.out.println(fib.fib4(n));
        });
        Times.test("fib5",() -> {
            System.out.println(fib.fib5(n));
        });
    }

    /**
     * 递归
     * 1+2+3+...+(n-1)+n的值(n>0)
     * @param n
     * @return
     */
    static int sum(int n) {
        if (n <= 1) return n;
        return n + sum(n - 1);
    }

    /**
     * 非递归
     * @param n
     * @return
     */
    static  int sum1(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }
    static  int sum2(int n) {
        if (n <= 1) return n;
        return (1 + n) * n >> 1;
    }
}