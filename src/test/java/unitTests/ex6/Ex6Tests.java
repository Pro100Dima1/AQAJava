package unitTests.ex6;

import org.expressJava.task10.ex6.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class Ex6Tests {

    @ParameterizedTest(name = "Проврка валидных имейлов")
    @ValueSource(strings = {"test@example.com"})
    public void testValidEmail(String initialEmail) {
        assertTrue(Main.isValidEmail(initialEmail));
    }

    @ParameterizedTest(name = "Проврка невалидных имейлов")
    @ValueSource(strings = {"bad@.com", "no-at-symbol"})
    public void testInalidEmail(String initialEmail) {
        assertFalse(Main.isValidEmail(initialEmail));
    }

    @ParameterizedTest(name = "Проврка имейлов на null")
    @ValueSource(strings = {"bad@.com", "no-at-symbol"})
    public void testNullEmail(String initialEmail) {
        assertFalse(Main.isValidEmail(null));
    }
}
