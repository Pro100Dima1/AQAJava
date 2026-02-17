package org.cleanCode.task2.fabricmethod.weapon;

public class Bow extends Weapon{

    @Override
    void damage() {
        System.out.println("Bow has 100 damage");
    }
}
