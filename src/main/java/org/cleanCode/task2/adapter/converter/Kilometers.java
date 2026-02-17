package org.cleanCode.task2.adapter.converter;

public class Kilometers implements Distance {

    @Override
    public void distance(int km) {
        System.out.println("Work with " + km + " km");
    }
}
