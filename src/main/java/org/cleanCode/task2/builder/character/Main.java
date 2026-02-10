package org.cleanCode.task2.builder.character;

public class Main {
    public static void main(String[] args) {
        Character barbarian = new Character.CharBuilder()
                .setArmor(200)
                .setDamage(100)
                .setHp(50)
                .setMagicType("Fire")
                .build();

        System.out.println(barbarian);

    }
}
