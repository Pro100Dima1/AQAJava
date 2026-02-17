package org.cleanCode.task2.fabricmethod.weapon;

public class Sword extends Weapon{

    @Override
    void damage() {
        System.out.println("Sword has 50 damage");
    }
}
