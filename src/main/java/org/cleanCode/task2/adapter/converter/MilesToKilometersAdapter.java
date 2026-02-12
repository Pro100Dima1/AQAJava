package org.cleanCode.task2.adapter.converter;

public class MilesToKilometersAdapter implements Distance {
    private Miles miles;

    public MilesToKilometersAdapter(Miles miles) {
        this.miles = miles;
    }

    @Override
    public void distance(int km) {
        System.out.println("Converting miles to kilometers");
        miles.distance(km);
    }
}
