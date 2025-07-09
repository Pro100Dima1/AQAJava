package org.example.task5.zoo;

public class Main {
    public static void main(String[] args) {
        Zookeeper manager = new Zookeeper();
        Bird raven = new Bird();
        Elephant elephant = new Elephant();

        manager.setAnimal(raven);
        manager.showBehaviourAnimal();

        manager.setAnimal(elephant);
        manager.showBehaviourAnimal();
    }
}
