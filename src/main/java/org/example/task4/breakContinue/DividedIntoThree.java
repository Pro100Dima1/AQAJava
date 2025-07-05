package org.example.task4.breakContinue;

public class DividedIntoThree {
    public static void main(String[] args) {
        divideThreeNumbers();
    }

    public static void divideThreeNumbers() {
        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0) {
                continue;
            } else {
                System.out.println(i);
            }
        }
    }
}
