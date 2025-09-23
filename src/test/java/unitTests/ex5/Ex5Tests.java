package unitTests.ex5;

import org.expressJava.task10.ex5.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ex5Tests {

    @ParameterizedTest(name = "Проверка является ли год високосным")
    @ValueSource(ints = {2020, 2000, 1600})
    public void checkIsLeapYear(int year) {
        assertTrue(Main.isLeapYear(year));
    }

    @ParameterizedTest(name = "Проверка НЕ явялется ли год високосным")
    @ValueSource(ints = {2019, 2021, 1900})
    public void checkIsNotLeapYear(int year) {
        assertFalse(Main.isLeapYear(year));
    }
}
