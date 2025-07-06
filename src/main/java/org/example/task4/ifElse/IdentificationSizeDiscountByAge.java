package org.example.task4.ifElse;

import java.util.Scanner;

public class IdentificationSizeDiscountByAge {
    public static void main(String[] args) {
        System.out.println(ageOfClient());
    }

    public static int ageOfClient() {
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();

        int discount = 0;
        if (age < 18) {
            discount = 25;
        }else if (age >= 65) {
            discount = 30;
        } else {
            discount = 0;
        }
        return discount;
    }
}
