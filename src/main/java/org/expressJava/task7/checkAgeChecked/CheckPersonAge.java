package org.expressJava.task7.checkAgeChecked;

public class CheckPersonAge {
    public static void main(String[] args) {
        try {
            checkAge(new Person(1, "Masha"));
        } catch (InvalidAgeExcaption e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkAge(Person person) throws InvalidAgeExcaption {
        if (person.age > 0 && person.age < 150) {
            System.out.println("Вы хороший пользователь " + person.name);
        } else {
            throw new InvalidAgeExcaption("Вы слишком старый или молодой");
        }
    }
}
