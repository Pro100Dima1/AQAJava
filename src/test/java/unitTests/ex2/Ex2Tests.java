package unitTests.ex2;

import org.expressJava.task10.ex2.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ex2Tests {

    @ParameterizedTest(name = "Проверка подсчета гласных в разных стркоах")
    @CsvSource({"'hello', 2", "'java', 2", "'AEIOU', 5", "'', 0", "'bbb', 0"})
    public void testCountChar(String words, int expectedCount){
        int actualResult = Main.countVowels(words);
        assertEquals(expectedCount, actualResult);
    }
}
