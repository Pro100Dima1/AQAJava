package unitTests.ex4;

import org.expressJava.task10.ex4.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Ex4Tests {

    public static Stream<Arguments> streamOfArraysForTest() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 15, 4, 5}, 15),
                Arguments.of(new int[]{1}, 1),
                Arguments.of(new int[]{-1, -2, -3, -4, -15}, -1)
        );
    }

    @ParameterizedTest(name = "Првоерка поиска максимального числа в массивах с положительными, отрицательными и одним элементом")
    @MethodSource("streamOfArraysForTest")
    public void checkFindMaxNumberInArray(int[] array, int expectedResult) {
        assertEquals(expectedResult, Main.findMax(array));
    }

    @Test
    @DisplayName("Проверка поиска максимального числа в пустом массиве")
    public void checkFindMaxNumberInEmptyArray() {
        assertThrows(NullPointerException.class, () -> Main.findMax(null));
    }
}
