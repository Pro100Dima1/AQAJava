package org.example.task8.functionalInterface;

public class MathOperation {
    public static void main(String[] args) {
        mathOperation summation = ((firstNumber, secondNumber) -> firstNumber + secondNumber);
        mathOperation division = ((firstNumber, secondNumber) -> firstNumber / secondNumber);
        mathOperation subtraction = ((firstNumber, secondNumber) -> firstNumber - secondNumber);
        mathOperation multiplication = ((firstNumber, secondNumber) -> firstNumber * secondNumber);
        System.out.println("Сумма = " + summation.operation(10, 5) + " Деление = " + division.operation(10, 5) + " Выитание = " + subtraction.operation(10, 5) + " Умножение =" + multiplication.operation(10, 5));


    }
    @FunctionalInterface
    interface mathOperation {
        int operation(int firstNumber, int secondNumber);
    }
}



