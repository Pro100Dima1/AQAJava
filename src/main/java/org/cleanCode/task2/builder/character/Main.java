package org.cleanCode.task2.builder.character;

public class Main {
    public static void main(String[] args) {
        Character barbarian = new Character.CharBuilder()
                .setDamage(500)
                .setHp(1000)
                .setArmor(200)
                .build();
        System.out.println(barbarian);
    }
}
