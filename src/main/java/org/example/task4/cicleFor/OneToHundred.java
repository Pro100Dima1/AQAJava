package org.example.task4.cicleFor;

public class OneToHundred {
    public static void main(String[] args) {
        numbersParityThree();
    }

    public static void numbersParityThree() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.println(i);
            }
        }
    }
}
