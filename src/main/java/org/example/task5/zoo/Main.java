package org.example.task5.zoo;

public class Main {
    public static void main(String[] args) {
        Elephant mark = new Elephant();
        Bird marina = new Bird();
        Zookeeper adminEmployee = new Zookeeper();

        adminEmployee.addAnimal(mark);
        adminEmployee.showAnimalBehavior();

        adminEmployee.addAnimal(marina);
        adminEmployee.showAnimalBehavior();
    }
}
