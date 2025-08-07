package org.example.task8.functionalInterface;

public class MathOperation {
    public static void main(String[] args) {
        mathOperation sum = ((firstNumber, secondNumber) -> firstNumber + secondNumber);
        System.out.println(sum.operation(2, 6));
    }

    @FunctionalInterface
    public interface mathOperation {
        int operation(int firstNumber, int secondNumber);
    }
}
