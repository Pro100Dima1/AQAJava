package generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomData {
    private RandomData() {}

    public static String getName() {
        String firstName = getNamePart();
        String lastName = getNamePart();
        return firstName + " " + lastName;
    }

    private static String getNamePart() {
        return RandomStringUtils.randomAlphabetic(1).toUpperCase()
                + RandomStringUtils.randomAlphabetic(4, 14).toLowerCase();
    }

    public static String getInvalidName() {
        return RandomStringUtils.randomAlphabetic(1);
    }

    public static String getPassword() {
        return RandomStringUtils.randomAlphabetic(3).toUpperCase() +
                RandomStringUtils.randomAlphabetic(5).toLowerCase() +
                RandomStringUtils.randomNumeric(3) + "$";
    }

    public static float getBalance() {
         Random random = new Random();
         return random.nextFloat() * 5000;
    }

    public static float getRandomBalance() {
        Random random = new Random();
        return random.nextFloat(5000F);
    }
}
