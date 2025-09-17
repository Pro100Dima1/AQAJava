package org.expressJava.task7.regexEmail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class checkEmailRegex {
    public static void main(String[] args) {
        String email = "Qwerty@yandex.ru";
        String regexEmail = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

        try {
            checkEmail(email, regexEmail);
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkEmail(String email, String regexEmail) {
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new InvalidEmailException("Невалидный имейл");
        } else {
            System.out.println("Имейл велиrолепный");
        }
    }
}
