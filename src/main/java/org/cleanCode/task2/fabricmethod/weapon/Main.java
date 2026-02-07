package org.cleanCode.task2.fabricmethod.weapon;

public class Main {
    public static void main(String[] args) {
        WeaponFactory weaponFactory;

        String swordType = "150";

        if (swordType.equalsIgnoreCase("100")) {
            weaponFactory = new SwordFactory();
        } else if (swordType.equalsIgnoreCase("150")) {
            weaponFactory = new BowFactory();
        } else if (swordType.equalsIgnoreCase("500")) {
            weaponFactory = new GunFactory();
        } else {
            throw new RuntimeException("Not found weapon for create");
        }
        weaponFactory.planWeapon();
    }
}
