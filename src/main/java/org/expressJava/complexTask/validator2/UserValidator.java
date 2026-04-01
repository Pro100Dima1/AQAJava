package org.expressJava.complexTask.validator2;

public class UserValidator {
    public static void main(String[] args) {
        User user = new User("Vitya", "oao@yandex.mail", 18);
        checkUser(true, user);
    }

    public static void checkUser(boolean validationEnabled, User user) {
        if (validationEnabled) {
            try {
                checkName(user);
            } catch (InvalidUserException e) {
                System.err.println(e.getMessage());
            }
            try {
                checkAge(user);
            } catch (InvalidUserException e) {
                System.err.println(e.getMessage());
            }
            try {
                checkEmail(user);
            } catch (InvalidUserException e) {
                System.err.println(e.getMessage());
            }
        }else {
            System.out.println("Validation failing");
        }
    }

    public static boolean checkName(User user) throws InvalidUserException {
        boolean isValidName = false;
        if (user.getName2() != null && !user.getName2().isEmpty()) {
            if (user.getName2().charAt(0) == user.getName2().toUpperCase().charAt(0)) {
                isValidName = true;
                System.out.println("Username is valid");
            } else {
                throw new InvalidUserException("Username must start with UpperCase");
            }
        } else {
            throw new InvalidUserException("Username cannot be empty");
        }
        return isValidName;
    }

    public static boolean checkAge(User user) throws InvalidUserException {
        boolean isValidAge = false;
        int minAge = 18;
        int maxAge = 100;
        if (user.getAge2() >= minAge && user.getAge2() <= maxAge) {
            System.out.println("Age is valid");
            isValidAge = true;
        } else {
            throw new InvalidUserException("Age must be > " + minAge + " and < " + maxAge);
        }
        return isValidAge;
    }

    public static boolean checkEmail(User user) throws InvalidUserException {
        boolean isValidEmail = false;
        if (user.getEmail2().matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            isValidEmail = true;
            System.out.println("Email is valid !");
        } else {
            throw new InvalidUserException("Email is not valid");
        }
        return isValidEmail;
    }
}

