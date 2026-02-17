package org.cleanCode.task2.adapter.converter;

public class Main {
    public static void main(String[] args) {
        Miles miles = new Miles();
        Distance adapter = new MilesToKilometersAdapter(miles);
        adapter.distance(100);
    }
}
