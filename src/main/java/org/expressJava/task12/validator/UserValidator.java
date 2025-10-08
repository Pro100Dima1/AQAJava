package org.expressJava.task12.validator;

//1 Проверка имени: Имя должно быть не пустым и начинаться с заглавной буквы.
//2 Проверка возраста: Возраст должен быть в пределах от 18 до 100 лет.
//3 Проверка email: Email должен соответствовать стандартному формату электронной почты.
//4 Управление валидацией: Валидация данных должна происходить только если флаг validationEnabled установлен в true.
//5 Исключения: При обнаружении невалидных данных необходимо выбрасывать InvalidUserException.

public class UserValidator {
    boolean validationEnabled = false;

    public static void main(String[] args) {
        System.out.println(checkValidationName(" "));
    }


    public static boolean checkValidationName(String name) {
        boolean nameIsValid = false;
        if (name.charAt(0) == name.toUpperCase().charAt(0) && !name.equals(" ")) {
            nameIsValid = true;
            System.out.println("Валидация имени пройдена успешно");
        }
        return nameIsValid;
    }

}
