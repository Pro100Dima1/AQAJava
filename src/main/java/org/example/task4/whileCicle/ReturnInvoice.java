package org.example.task4.whileCicle;

import java.util.Scanner;

public class ReturnInvoice {
    public static void main(String[] args) {
        invoice();
    }

    public static void invoice() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n > 0) {
            while (n >= 1) {
                System.out.println(n);
                n--;
            }
        } else {
            System.out.println("Вы ввели отрицательное число");
        }
    }
}
