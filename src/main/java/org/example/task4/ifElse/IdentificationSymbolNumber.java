package org.example.task4.ifElse;

import java.util.Scanner;

public class IdentificationSymbolNumber {
    public static void main(String[] args) {
        IdentificationSymbolNumber identificationSymbolNumber = new IdentificationSymbolNumber();
        System.out.println(identificationSymbolNumber.identificateSymbol());
    }

    public String identificateSymbol() {

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        String symbol = "";
        if (number > 0) {
            symbol = "Число положительное";
        }
        if (number < 0) {
            symbol = "Число отрицательное";
        }
        if (number == 0) {
            symbol = "Число равно 0";
        }
        return symbol;
    }
}
