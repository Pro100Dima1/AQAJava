package org.example.task5.pets;

public class Cat extends Pets{

    @Override
    public void feed() {
        System.out.println("Кошка ест мокрый корм");
    }

    @Override
    public void leisure() {
        System.out.println("Кошка гуляет");
    }
}
