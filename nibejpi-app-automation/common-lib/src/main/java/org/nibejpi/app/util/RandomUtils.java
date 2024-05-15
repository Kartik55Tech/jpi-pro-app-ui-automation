package org.nibejpi.app.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    /**
     * Generates a random name by appending a random alphabetic string to a fixed prefix.
     *
     * @return Randomly generated name.
     */
    public String generateRandomName() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return "Mobile Automation" + " " + generatedString;
    }

    /**
     * Generates a random 6-digit zip code.
     *
     * @return Randomly generated zip code.
     */
    public String generateRandomZipCode() {
        String generatedString2 = RandomStringUtils.randomNumeric(6);
        return generatedString2;
    }

    /**
     * Generates a random alphanumeric string by combining a random alphabetic and numeric string.
     *
     * @return Randomly generated alphanumeric string.
     */
    public String randomAlphaNumeric() {
        String st = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(3);

        return st + "@" + num;
    }

    /**
     * Generates a random email address with a random numeric part and random alphabetic domain.
     *
     * @return Randomly generated email address.
     */
    public String generateRandomEmail() {
        String StNum = RandomStringUtils.randomNumeric(2);
        String StDomain = RandomStringUtils.randomAlphabetic(4);
        return "Mobileautomation" + StNum + "@" + StDomain + ".com";
    }

    /**
     * Generates a random 10-digit phone number.
     *
     * @return Randomly generated phone number.
     */
    public String generateRandomPhoneNumber() {
        String number = RandomStringUtils.randomNumeric(10);
        return number;
    }

    /**
     * Generates a random service partner name by appending a random numeric string to a fixed prefix.
     *
     * @return Randomly generated service partner name.
     */
    public String generateRandomServicePartnerName() {
        String SPName = RandomStringUtils.randomNumeric(3);
        return "Mobile Automation Test" + " " + SPName;
    }
}
