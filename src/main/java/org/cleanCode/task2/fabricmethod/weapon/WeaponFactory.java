package org.cleanCode.task2.fabricmethod.weapon;

public abstract class WeaponFactory {
    abstract Weapon createWeapon();

    public void planWeapon(){
        Weapon weapon = createWeapon();

        weapon.damage();
    }
}
