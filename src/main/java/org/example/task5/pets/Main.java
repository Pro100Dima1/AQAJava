package org.example.task5.pets;

public class Main {
    public static void main(String[] args) {
        Dog sheepdog = new Dog();
        Cat sphinx = new Cat();
        PetsOwner host1 = new PetsOwner();

        host1.setPet(sheepdog);
        host1.interactionWithAPet();

        host1.setPet(sphinx);
        host1.interactionWithAPet();
    }
}
