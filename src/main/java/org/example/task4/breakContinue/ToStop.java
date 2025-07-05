package org.example.task4.breakContinue;

import java.util.Scanner;

public class ToStop {
    public static void main(String[] args) {
        comands();
    }

    public static void comands() {
        Scanner scanner = new Scanner(System.in);
        String comand = "";

        while (true) {
            comand = scanner.nextLine();
            if (comand.equals("stop")) {
                System.out.println("Вы ввели: " + comand);
                break;
            }
        }
    }
}
