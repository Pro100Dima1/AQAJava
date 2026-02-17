package org.cleanCode.task2.fabricmethod.weapon;

public class Main {
    public static void main(String[] args) {
        WeaponFactory weaponFactory;

        String damage = "100";

        if (damage.equalsIgnoreCase("100")) {
            weaponFactory = new BowFactory();
        } else if (damage.equalsIgnoreCase("50")) {
            weaponFactory = new SwordFactory();
        } else if (damage.equalsIgnoreCase("200")) {
            weaponFactory = new GunFactory();
        } else {
            throw new RuntimeException("Weapon not found");
        }
        weaponFactory.planWeapon();
    }
}
