package org.cleanCode.task2.fabricmethod.weapon;

public class Gun extends Weapon{
    @Override
    public void damage() {
        System.out.println("The gun has 500 damage");
    }
}
