package org.cleanCode.task2.fabricmethod.weapon;

public class GunFactory extends WeaponFactory{
    @Override
    public Weapon createWeapon() {
        return new Gun();
    }
}
