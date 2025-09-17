package org.expressJava.task5.zoo;

public class Bird extends Animal{

    @Override
    public void sound() {
        System.out.println("Птичка чирикает");
    }

    @Override
    public void walk() {
        System.out.println("Птичка летает");
    }
}
