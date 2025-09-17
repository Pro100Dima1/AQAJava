package org.expressJava.task6.arrayListEx;

import java.util.ArrayList;
import java.util.Arrays;

public class Example3 {
    public static void main(String[] args) {
        ArrayList<String> stringi = new ArrayList<>(Arrays.asList("123", "1234", "12345", "123456789", "12345", "1234", "123"));

        ArrayList<String> stringi2 = new ArrayList<>();
        stringi2.add("13123");
        stringi2.add("23");
        stringi2.add("34546462");
        stringi2.add("22");
        stringi2.add("1123");
// Вариант 1 когда создаем ArrayList на основе массива строк
        String max = "";
        for (String i : stringi) {
            if (i.length() > max.length()) {
                max = i;
            }
        }
        System.out.println(max);

// Вариант 2 когда создаем пустой ArrayList и его заполняем
        String max2 = "";
        for (String i : stringi2) {
            if (i.length() > max2.length()) {
                max2 = i;
            }
        }
        System.out.println(max2);
    }
}
