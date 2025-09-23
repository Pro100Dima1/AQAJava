package unitTests.ex1;

import org.expressJava.task10.ex1.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Ex1Tests {

    @ParameterizedTest(name = "Позитивные проверки на четность")
    @ValueSource(ints = {2, 4, 6, 8, 0, -2, -4, -6})
    public void checkIsParityNumber(int inputNumber) {
        boolean actualResult = Main.isEven(inputNumber);
        assertTrue(actualResult);
    }

    @ParameterizedTest(name = "Позитивные проверки на НЕ четность")
    @ValueSource(ints = {1, 3, 5, 7})
    public void checkIsNotParityNumber(int inputNumber){
        boolean actualResult = Main.isEven(inputNumber);
        assertFalse(actualResult);
    }
}
