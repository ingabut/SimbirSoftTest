package com.simbirsoft.utils;

public class FuncUtils {
    public static int fibonacciCalc(int num) {
        if (num == 1) {
            return 0;
        }
        if (num < 4) {
            return 1;
        }
        int prev = 1, curr = 1;
        for (int i = 4; i <= num ; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
