package org.cleanCode.task2.builder.character;

public class Character {
    private int hp;
    private int armor;
    private int damage;
    private String magicType;

    public Character(int hp, int armor, int damage, String magicType) {
        this.hp = hp;
        this.armor = armor;
        this.damage = damage;
        this.magicType = magicType;
    }

    public Character(CharBuilder charBuilder) {
        this.hp = charBuilder.hp;
        this.armor = charBuilder.armor;
        this.damage = charBuilder.damage;
        this.magicType = charBuilder.magicType;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setMagicType(String magicType) {
        this.magicType = magicType;
    }

    @Override
    public String toString() {
        return "Character{" +
                "hp=" + hp +
                ", armor=" + armor +
                ", damage=" + damage +
                ", magicType='" + magicType + '\'' +
                '}';
    }

    static class CharBuilder{
        private int hp;
        private int armor;
        private int damage;
        private String magicType;

        public CharBuilder setHp(int hp) {
            this.hp = hp;
            return this;
        }

        public CharBuilder setArmor(int armor) {
            this.armor = armor;
            return this;
        }

        public CharBuilder setDamage(int damage) {
            this.damage = damage;
            return this;
        }

        public CharBuilder setMagicType(String magicType) {
            this.magicType = magicType;
            return this;
        }

        public Character build(){
            return new Character(this);
        }
    }
}
