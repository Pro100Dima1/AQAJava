package org.expressJava.task12.validator;

//1 Проверка имени: Имя должно быть не пустым и начинаться с заглавной буквы.
//2 Проверка возраста: Возраст должен быть в пределах от 18 до 100 лет.
//3 Проверка email: Email должен соответствовать стандартному формату электронной почты.
//4 Управление валидацией: Валидация данных должна происходить только если флаг validationEnabled установлен в true.
//5 Исключения: При обнаружении невалидных данных необходимо выбрасывать InvalidUserException.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static void main(String[] args) {
        boolean validationEnabled = true;
        if (validationEnabled) {
            User user1 = new User(19, "Bob", "bob@mail.com");
            try {
                System.out.println(checkValidationName(user1));
            } catch (InvalidUserException e) {
                System.err.println(e.getMessage());
            }
            try {
                System.out.println(checkValidationAge(user1));
            } catch (InvalidUserException e) {
                System.err.println(e.getMessage());
            }
            try {
                System.out.println(checkValidationEmail(user1));
            } catch (InvalidUserException e) {
                System.err.println(e.getMessage());
            }
        }
        else {
            System.out.println("Validation is not working");
        }
    }

    public static boolean checkValidationName(User user) throws InvalidUserException {

        boolean nameIsValid = false;
        if (user.getName().charAt(0) == user.getName().toUpperCase().charAt(0) && !user.getName().equals(" ")) {
            nameIsValid = true;
            System.out.println("Check Name Success");
        } else {
            throw new InvalidUserException("The name must be non-empty and start with a capital letter");
        }
        return nameIsValid;
    }

    public static boolean checkValidationAge(User user) throws InvalidUserException {
        boolean ageIsValid = false;
        if (user.getAge() > 18 || user.getAge() < 100) {
            ageIsValid = true;
            System.out.println("Check Age Succsess");
        } else {
            throw new InvalidUserException("The age limit is between 18 and 100 years old.");
        }
        return ageIsValid;
    }

    public static boolean checkValidationEmail(User user) throws InvalidUserException {
        boolean emailIsValid = false;
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$");
        Matcher matcher = pattern.matcher(user.getEmail());
        if (matcher.matches()) {
            emailIsValid = true;
            System.out.println("Check Email Succsess");
        } else {
            throw new InvalidUserException("Email must comply with the standard email format.");
        }
        return emailIsValid;
    }
}
