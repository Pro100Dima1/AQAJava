package unitTests.ex8;

import org.expressJava.task10.ex8.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Ex8Tests {

    public static Stream<Arguments> arraysForTests() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, 3),
                Arguments.of(new int[]{1, 2, 4, 5}, 4)
        );
    }

    @ParameterizedTest(name = "Проверка обычных массивов")
    @MethodSource("arraysForTests")
    public void checkFindSecondMaxNumber(int[] array, int expectedResult) {
        assertEquals(expectedResult, Main.findSecondMax(array));
    }

    @Test
    @DisplayName("Проверка массива с одним элементом")
    public void checkArrayWithOneElement() {
        int[] array = {1};
        assertThrows(IllegalArgumentException.class, () -> Main.findSecondMax(array));
    }

    @Test
    @DisplayName("Проверка массива с дубликатами")
    public void checlArrayWithDublicate() {
        int[] array = {1, 1, 2, 2, 3, 3, 4, 5, 6};
        assertThrows(NoSuchElementException.class, () -> Main.findSecondMax(array));
    }
}
