package complexTasks;

//Имя : 1) Успех. 2) ПУСТОЕ - Ексепшн. 3) с маленькой буквы - ексепшн
//Возраст : 1) Успех. 2) Меньше 18 - експешн. 3) Большое 100 - ексепшн (18 - успех. 100 - успех)
//Имейл : 1) Успех. 2) Разные варианты не проходящие регулярку - експешн.

import org.expressJava.task12.validator.InvalidUserException;
import org.expressJava.task12.validator.User;
import org.expressJava.task12.validator.UserValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserValidatorTest {
    UserValidator userValidator = new UserValidator();

    @Test
    @DisplayName("Проверка валидации валидного имени")
    public void checkValidName(){
        User user = new User(20, "Sam", "22@mail.com");
        assertDoesNotThrow(() -> userValidator.checkValidationName(user));
    }

    @Test
    @DisplayName("Проверка валидации при пустом имени")
    public void checkEmptyName(){
        User user = new User(20, "", "23@mail.com");
        assertThrows(InvalidUserException.class, () -> userValidator.checkValidationName(user));
    }

    @Test
    @DisplayName("Проверка валидации имени с маленькой буквы")
    public void checkSmallCharName(){
        User user = new User(20, "sam", "23@mail.com");
        assertThrows(InvalidUserException.class, () -> userValidator.checkValidationName(user));
    }

    @Test
    @DisplayName("Проверка валидации валидного возраста")
    public void checkValidAge(){
        User user = new User(20, "Sam", "22@mail.com");
        assertDoesNotThrow(() -> userValidator.checkValidationAge(user));
    }

    @Test
    @DisplayName("Проверка валидации меньше 18 лет")
    public void checkYoungestdAge(){
        User user = new User(2, "Sam", "22@mail.com");
        assertThrows(InvalidUserException.class, () -> userValidator.checkValidationAge(user));
    }

    @Test
    @DisplayName("Проверка валидации больше 100 лет")
    public void checkOldestAge(){
        User user = new User(200, "Sam", "22@mail.com");
        assertThrows(InvalidUserException.class, () -> userValidator.checkValidationAge(user));
    }

    @Test
    @DisplayName("Проверка валидации 18 лет")
    public void checkEighteenAge(){
        User user = new User(18, "Sam", "22@mail.com");
        assertDoesNotThrow(() -> userValidator.checkValidationAge(user));
    }

    @Test
    @DisplayName("Проверка валидации 100 лет")
    public void checkHundredAge(){
        User user = new User(100, "Sam", "22@mail.com");
        assertDoesNotThrow(() -> userValidator.checkValidationAge(user));
    }

    @Test
    @DisplayName("Проверка валидации валидного имейла")
    public void checkValidEmail(){
        User user = new User(100, "Sam", "22@mail.com");
        assertDoesNotThrow(() -> userValidator.checkValidationEmail(user));
    }

    @Test
    @DisplayName("Проверка валидации невалидного имейла")
    public void checkInvalidEmail(){
        User user = new User(100, "Sam", "22.com");
        assertThrows(InvalidUserException.class, () -> userValidator.checkValidationEmail(user));
    }
}
