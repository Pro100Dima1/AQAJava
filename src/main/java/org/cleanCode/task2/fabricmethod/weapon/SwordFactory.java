package org.cleanCode.task2.fabricmethod.weapon;

public class SwordFactory extends WeaponFactory{

    @Override
    Weapon createWeapon() {
        return new Sword();
    }
}
