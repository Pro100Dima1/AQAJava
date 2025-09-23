package unitTests.ex2;

import org.expressJava.task10.ex2.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Ex2Tests {

    @ParameterizedTest(name = "Проверка подсчета гласных в разных строках")
    @CsvSource({"'hello', 2", "'java', 2", "'AEIOU', 5", "'bbb', 0", "'', 0"})
    public void checkCountCharInWords(String word, int expectedResult){
        int actualResult = Main.countVowels(word);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Проверка подсчета гласных в строках при получении null")
    public void checkIsNullString(){
        assertThrows(IllegalArgumentException.class, () -> Main.countVowels(null), "Если передали null, то выбрасываем исключение");
    }

}
