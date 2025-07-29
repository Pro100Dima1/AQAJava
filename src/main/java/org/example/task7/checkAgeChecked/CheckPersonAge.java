package org.example.task7.checkAgeChecked;

public class CheckPersonAge {
    public static void main(String[] args) {
        try {
            chackAge(new Person(15, "Ivan"));
        } catch (InvalidAgeExcaption e) {
            throw new RuntimeException(e);
        }
    }

    public static void chackAge(Person person) throws InvalidAgeExcaption {
        if (person.age > 0 && person.age < 150) {
            System.out.println("Вы хороший пользователь, " + person.name);
        } else {
            throw new InvalidAgeExcaption("Вы слишком стары или молоды");
        }
    }
}
