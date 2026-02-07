package org.cleanCode.task2.fabricmethod.weapon;

public class SwordFactory extends WeaponFactory{
    @Override
    public Weapon createWeapon() {
        return new Sword();
    }
}
