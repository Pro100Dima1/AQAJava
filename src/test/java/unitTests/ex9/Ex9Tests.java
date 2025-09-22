package unitTests.ex9;

import org.expressJava.task10.ex9.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class Ex9Tests {

    @ParameterizedTest(name = "Проверка текста на колличество слов")
    @CsvSource({"'Привет Мир', 2", "'Я очень люблю Java', 4", "'', 0"})
    public void checkCountWords(String string, int expectedCount){
        assertEquals(expectedCount, Main.countWords(string));
    }

    @Test
    @DisplayName("Проверка строки на null")
    public void checkNullString(){
        assertThrows(NullPointerException.class, () -> Main.countWords(null));
    }
}
