package unitTests.ex7;

import org.expressJava.task10.ex7.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Ex7Tests {

    @ParameterizedTest(name = "Проверка факториала положительных чисел")
    @CsvSource({"1, 1", "2, 2", "3, 6", "5, 120", "10, 3628800", "0, 1"})
    public void testFactorialPositiveNumber(int number, int expectedResult) {
        int actualResult = Main.factorial(number);
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest(name = "Проверка отрицательных чисел")
    @ValueSource(ints = {-1, -2, -4})
    public void testFactorialNegativeNumber(int number) {
        assertThrows(IllegalArgumentException.class, () -> Main.factorial(number),
                "Если пришло отрицательное число, то выкидываем исключение");
    }
}
