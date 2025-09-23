package unitTests.ex3;

import org.expressJava.task10.ex3.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Ex3Tests {

    @ParameterizedTest(name = "Тест на проверку переворота строки")
    @ValueSource(strings = {"qwerty", "qazwsx", "AQA", " "})
    public void testReversString(String word) {
        String expectedResult = new StringBuilder(word).reverse().toString();
        String actualResult = Main.reverse(word);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Проверка переворота строки при получении null")
    public void checkStringIsNull(){
        assertNull(Main.reverse(null));
    }
}
