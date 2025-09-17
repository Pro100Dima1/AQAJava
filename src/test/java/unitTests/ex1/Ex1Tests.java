package unitTests.ex1;

import org.expressJava.task10.ex1.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Ex1Tests {
//    1) Является инпут четным + отрицательные
//    2) является ли инпут нечетным + отрицательные

    @ParameterizedTest(name = "Позитивные проверки на четность")
    @ValueSource(ints = {2, 4, 0, 10, -2, -10})
    public void inputIsParity(int input) {
        boolean actualResult = Main.isEven(input);
        assertTrue(actualResult);
    }

    @ParameterizedTest(name = "Позитивные проверки на НЕчетность")
    @ValueSource(ints = {1, 3, 5, -3, -5})
    public void inputIsNotParity(int input) {
        boolean actualResult = Main.isEven(input);
        assertFalse(actualResult);
    }
}
