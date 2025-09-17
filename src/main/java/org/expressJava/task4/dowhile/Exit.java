package org.expressJava.task4.dowhile;

import java.util.Scanner;

public class Exit {
    public static void main(String[] args) {
        exit();
    }

    public static void exit() {
        Scanner scanner = new Scanner(System.in);
        String comand;

        do {
            System.out.println("Введите нужную команду");
            comand = scanner.nextLine();
        } while (!comand.equals("exit"));
    }
}
