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
                Arguments.of(new int[]{1, -2, -5, 3, 2, 0}, 2)
        );
    }

    @ParameterizedTest(name = "Проверка обычых массивов")
    @MethodSource("arraysForTests")
    public void checkFindSecondMaxNumber(int[] array, int expectedNumber) {
        assertEquals(expectedNumber, Main.findSecondMax(array));
    }

    @Test
    @DisplayName("Првоерка массива с одинаковыми элементами")
    public void checkArrayWithDublicate() {
        int[] arrayWithDublicate = {1, 1, 2, 2};
        assertThrows(NoSuchElementException.class, () -> Main.findSecondMax(arrayWithDublicate),
                "Если в массиве есть одинаковые элементы, то кидаем исключение");
    }

    @Test
    @DisplayName("Проверка массива с одним элементом")
    public void checkArrayWithOneElement() {
        int[] arrayWithOneElement = {1};
        assertThrows(IllegalArgumentException.class, () -> Main.findSecondMax(arrayWithOneElement),
                "Если в массиве один элемент, то кидаем исключение");
    }
}
