package org.example.task7.chechAge;

public class CheckPersonAge {
    public static void main(String[] args) {
        try {
            chackAge(new Person(156, "Ivan"));
        } catch (invalidAgeExcaption e) {
            throw new RuntimeException(e);
        }
    }

    public static void chackAge(Person person) throws invalidAgeExcaption {
        if (person.age > 0 && person.age < 150) {
            System.out.println("Вы хороший пользователь");
        } else {
            throw new invalidAgeExcaption("Вы слишком стары или молоды");
        }
    }
}
