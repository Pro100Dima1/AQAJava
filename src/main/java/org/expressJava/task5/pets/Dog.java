package org.expressJava.task5.pets;

public class Dog extends Pets{

    @Override
    public void feed(){
        System.out.println("Собака ест сухой корм");
    }

    @Override
    public void leisure(){
        System.out.println("Собака гуляет");
    }

}
