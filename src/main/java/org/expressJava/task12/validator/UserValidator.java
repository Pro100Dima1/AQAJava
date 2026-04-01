package org.expressJava.task12.validator;

//1 Проверка имени: Имя должно быть не пустым и начинаться с заглавной буквы.
//2 Проверка возраста: Возраст должен быть в пределах от 18 до 100 лет.
//3 Проверка email: Email должен соответствовать стандартному формату электронной почты.
//4 Управление валидацией: Валидация данных должна происходить только если флаг validationEnabled установлен в true.
//5 Исключения: При обнаружении невалидных данных необходимо выбрасывать InvalidUserException.

public class UserValidator {
    public static void main(String[] args) {
        User userVitya = new User("Vitya", 18, "oao@yandex.ru");
        validate(userVitya, true);
    }

    public static void validate(User user, boolean enabled) {
        if (enabled) {
            try {
                checkValidationName(user);
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
            try {
                checkValidationAge(user);
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
            try {
                checkValidationEmail(user);
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Validation is not enabled");
        }
    }

    public static boolean checkValidationName(User user) throws InvalidUserException {
        boolean isValidName = false;
        if (user.getName() != null && !user.getName().isEmpty()) {
            if (user.getName().charAt(0) == user.getName().toUpperCase().charAt(0)) {
                isValidName = true;
                System.out.println("Name is valid");
            } else {
                throw new InvalidUserException("Name is not valid");
            }
        }
        return isValidName;
    }

    public static boolean checkValidationAge(User user) throws InvalidUserException {
        boolean isValidAge = false;
        if (user.getAge() >= 18 && user.getAge() <= 100) {
            isValidAge = true;
            System.out.println("Age is valid");
        } else {
            throw new InvalidUserException("Age is not valid");
        }
        return isValidAge;
    }

    public static boolean checkValidationEmail(User user) throws InvalidUserException {
        boolean isValidEmail = false;
        if (user.getEmail().matches("[\\w-.]+@[\\w-]+(.[\\w-]+)*.[a-z]{2,}$")) {
            isValidEmail = true;
            System.out.println("Email is valid");
        } else {
            throw new InvalidUserException("Email is not valid");
        }
        return isValidEmail;
    }
}
