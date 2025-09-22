package org.expressJava.task10.ex10;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\+\\d{1,3} \\d{10}");
    }
}
