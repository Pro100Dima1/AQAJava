package org.example.task7.regexEmail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class checkEmailRegex {
    public static void main(String[] args) {
        String email = "examplegmail.com";
        String regEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        try {
            checkEmail(email, regEmail);
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkEmail(String email, String regEmail) {
        Pattern pattern = Pattern.compile(regEmail);
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches()){
            throw new InvalidEmailException("Невалидный имейл");
        }else {
            System.out.println("Отличное мыло!");
        }
    }
}
