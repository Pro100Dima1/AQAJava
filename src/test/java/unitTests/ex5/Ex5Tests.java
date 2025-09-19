package unitTests.ex5;

import org.expressJava.task10.ex5.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ex5Tests {

    @ParameterizedTest(name = "Проверка являтеся ли год високосным")
    @ValueSource(ints={2016, 4, 2020, 2000, 2000})
    public void testLeapYear(int year) {
        boolean actualResult = Main.isLeapYear(year);
        assertTrue(actualResult);
    }

    @ParameterizedTest(name = "Проверка НЕ являтеся ли год високосным")
    @ValueSource(ints={2019, 2021, 1900})
    public void testNotLeapYear(int year) {
        boolean actualResult = Main.isLeapYear(year);
        assertFalse(actualResult);
    }
}
