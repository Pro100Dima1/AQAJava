package org.expressJava.task11;

public class DebugTask6 {
    public static void main(String[] args) {
        countdown(5);
    }

    public static void countdown(int n) {
        //System.out.println(n);
        for (int i = n; i > 0; i--) {
            System.out.println(i);
        }
    }
}
