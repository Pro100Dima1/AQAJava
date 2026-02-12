package org.cleanCode.task2.adapter.converter;

public class Main {
    public static void main(String[] args) {
        Kilometers kilometers = new Kilometers();
        kilometers.distance(100);

        System.out.println("-------------------");

        Miles miles = new Miles();
        Distance adapter = new MilesToKilometersAdapter(miles);
        adapter.distance(300);
    }
}
