package unitTests.ex10;

import org.expressJava.task10.ex10.Main;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class Ex10Tests {

    @ParameterizedTest(name = "Проверка корректных номеров")
    @CsvSource({"'+1 1234567890', true"})
    public void checkValidPhoneNumber(String phoneNumber, boolean expectedResult) {
        assertEquals(expectedResult, Main.isValidPhoneNumber(phoneNumber));
    }

    @ParameterizedTest(name = "Проверка НЕ корректных номеров")
    @ValueSource(strings = {"12345"})
    public void checkInvalidPhoneNumber(String phoneNumber) {
        assertFalse(Main.isValidPhoneNumber(phoneNumber));
    }
}
