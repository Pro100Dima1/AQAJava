package unitTests.ex9;

import org.expressJava.task10.ex9.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Ex9Tests {

    @ParameterizedTest(name = "Проверка строк")
    @CsvSource({"'Hello World', 2", "'Я люблю Java', 3", "'', 0", "'    ', 0"})
    public void checkWordCount(String word, int expectedResult) {
        assertEquals(expectedResult, Main.countWords(word));
    }

    @Test
    @DisplayName("Проверка строки на null")
    public void checkNullString() {
        assertThrows(NullPointerException.class, () -> Main.countWords(null), "Если получаем null, то выкидываем исключение");
    }
}
