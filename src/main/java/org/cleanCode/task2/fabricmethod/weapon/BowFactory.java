package org.cleanCode.task2.fabricmethod.weapon;

public class BowFactory extends WeaponFactory{

    @Override
    Weapon createWeapon() {
        return new Bow();
    }
}
