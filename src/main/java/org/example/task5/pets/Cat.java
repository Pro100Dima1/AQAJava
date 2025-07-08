package org.example.task5.pets;

public class Cat extends Pets{
    @Override
    public void leisure() {
        System.out.println("Котик играет");
    }

    @Override
    public void feed() {
        System.out.println("Котик кушает мокрый корм");
    }
}
