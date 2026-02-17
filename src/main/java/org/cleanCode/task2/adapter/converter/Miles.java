package org.cleanCode.task2.adapter.converter;

public class Miles implements Distance {
    @Override
    public void distance(int km) {
        System.out.println("Work with " + km + " miles");
    }
}
