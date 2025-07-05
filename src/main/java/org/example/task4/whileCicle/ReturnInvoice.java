package org.example.task4.whileCicle;

import java.util.Scanner;

public class ReturnInvoice {
    public static void main(String[] args) {
        invoice();
    }

    public static void invoice() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        if (number > 0) {
            while (number >= 1) {
                System.out.println(number);
                number--;
            }
        } else {
            System.out.println("Сегодня принимаются только положительные числа");
        }
    }
}
