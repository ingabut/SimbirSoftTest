package com.simbirsoft.utils;

public class FuncUtils {
    public static int fibonacciCalc(int num) {
        if (num <= 1) {
            return num;
        }
        int prev = 0, curr = 1;
        for (int i = 0; i < num - 1; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
