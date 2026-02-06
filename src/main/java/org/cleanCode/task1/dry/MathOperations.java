package org.cleanCode.task1.dry;

public class MathOperations {
    public int summations(int... numbers) {
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return sum;
    }
}
