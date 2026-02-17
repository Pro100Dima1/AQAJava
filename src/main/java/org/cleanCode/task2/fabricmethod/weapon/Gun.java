package org.cleanCode.task2.fabricmethod.weapon;

public class Gun extends Weapon{

    @Override
    void damage() {
        System.out.println("Gun has 200 damage");
    }
}
