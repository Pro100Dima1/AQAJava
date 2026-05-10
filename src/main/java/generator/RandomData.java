package generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomData {
    private RandomData() {}

    public static String getName(){
        return RandomStringUtils.randomAlphabetic(10);
    }

    public static String getPassword(){
        return RandomStringUtils.randomAlphabetic(3).toUpperCase() +
                RandomStringUtils.randomAlphabetic(5).toLowerCase() +
                RandomStringUtils.randomNumeric(3) + "$";
    }

    public static float getBalance(){
         Random random = new Random();
         return random.nextFloat() * 5000;
    }
}
