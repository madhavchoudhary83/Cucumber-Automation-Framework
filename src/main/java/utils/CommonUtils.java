package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtils {

    public static String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
