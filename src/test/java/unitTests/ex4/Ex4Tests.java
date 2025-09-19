package unitTests.ex4;

import org.expressJava.task10.ex4.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Ex4Tests {

    public static Stream<Arguments> streamOfArraysForCheck() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, 4),
                Arguments.of(new int[]{5}, 5),
                Arguments.of(new int[]{-3, -5, -10}, -3)
        );
    }

    @ParameterizedTest(name = "Проверка на массив с положительными, отрицательными и одним элементом")
    // нельзя массивы использовать. см. доку метода @ValueSource(arrays)
    @MethodSource("streamOfArraysForCheck")
    public void checkFindMaxNumber(int[] array, int expectedArray) {
        assertEquals(expectedArray, Main.findMax(array));
    }

//    @Test
//    public void checkEmptyArray() {
//        assertThrows(IllegalArgumentException.class, () -> Main.findMax(null),
//                "Если пришел null, то кидаем исключение IllegalArgumentException");
//    }
}
