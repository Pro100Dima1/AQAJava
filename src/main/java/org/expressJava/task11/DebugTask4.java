package org.expressJava.task11;

public class DebugTask4 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("null"));
        System.out.println(isPalindrome("qwertytrewq"));
    }
    public static boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}

