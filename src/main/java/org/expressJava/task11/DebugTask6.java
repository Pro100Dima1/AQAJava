package org.expressJava.task11;

public class DebugTask6 {
    public static void main(String[] args) {
        int n = 5;
        countdown(n);
    }

    public static void countdown(int n) {
        for (int i = 0; i != 5; i++) {
            System.out.println(n);
            n -= 1;
        }
        //countdown(n - 1);
    }
}
