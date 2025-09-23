package unitTests.ex6;

import org.expressJava.task10.ex6.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class Ex6Tests {

    @ParameterizedTest(name = "Првоерка корректных имейлов")
    @ValueSource(strings = {"test@example.com"})
    public void checkValidEmail(String email) {
        assertTrue(Main.isValidEmail(email));
    }

    @ParameterizedTest(name = "Проверка некоректных имейлов")
    @ValueSource(strings = {"bad@.com", "no-at-symbol"})
    public void checkInvalidEmail(String email){
        assertFalse(Main.isValidEmail(email));
    }

    @Test
    @DisplayName("Првоерка на null")
    public void checkEmailIsNull(){
        assertFalse(Main.isValidEmail(null));
    }
}
