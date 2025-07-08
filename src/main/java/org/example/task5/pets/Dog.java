package org.example.task5.pets;

public class Dog extends Pets{
    @Override
    public void leisure() {
        System.out.println("Пёсик гуляет");
    }

    @Override
    public void feed() {
        System.out.println("Пёсик кушает сухой корм");
    }
}
