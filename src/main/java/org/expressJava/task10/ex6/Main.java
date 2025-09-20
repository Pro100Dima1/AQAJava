package org.expressJava.task10.ex6;

public class Main {
    public static void main(String[] args) {

    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }
}
