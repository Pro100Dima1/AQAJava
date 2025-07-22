package org.example.task6.linkedHashSet;

import java.util.LinkedHashSet;

public class LinkedHashSet1 {
    public static void main(String[] args) {
        LinkedHashSet<String> car = new LinkedHashSet<>();
        car.add("Ford");
        car.add("BMW");
        car.add("Mercedes");
        car.add("Lada");
        car.add("Ural");
        System.out.println(car);
    }
}
