package org.example.task4.dowhile;

import java.util.Scanner;

public class CheckPassword {
    public static void main(String[] args) {
        checkPassword();
    }

    public static void checkPassword() {
        Scanner scanner = new Scanner(System.in);
        int password;
        int currentPassword = 123321;

        do {
            System.out.println("Введите пароль");
            password = scanner.nextInt();
        } while (password != currentPassword);

    }
}
