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

        String symbolNumber = "";
        if (number > 0) {
            symbolNumber = "Число положительное";
        }
        if (number < 0) {
            symbolNumber = "Число отрицательное";
        }
        if (number == 0) {
            symbolNumber = "Число равно нулю";
        }
        return symbolNumber;
    }
}
