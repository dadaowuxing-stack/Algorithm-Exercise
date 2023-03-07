package com.fengshuoliu;

public class TailCall {

    int factorial(int n) {
        if (n <= 1) return n;
        return n * factorial(n - 1);
    }

    int factorial1(int n) {
        return factorial1(n, 1);
    }

    int factorial1(int n, int result) {
        if (n <= 1) return result;
        return factorial1(n - 1, result * n);
    }

    int fib(int n) {
        return fib(n, 1, 1);
    }

    int fib(int n, int first, int second) {
        if (n <= 1) return first;
        return fib(n - 1, second, first + second);
    }
}
