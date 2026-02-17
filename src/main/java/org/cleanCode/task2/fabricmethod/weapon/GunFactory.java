package org.cleanCode.task2.fabricmethod.weapon;

public class GunFactory extends WeaponFactory{

    @Override
    Weapon createWeapon() {
        return new Gun();
    }
}
