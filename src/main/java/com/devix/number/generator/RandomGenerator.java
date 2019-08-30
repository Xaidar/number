package com.devix.number.generator;

import java.util.Random;

public class RandomGenerator {

    private static Random rnd = new Random();

    public static String generate() {
        String alphabet = "АЕТОРНУКХСВМ";
        Random random = new Random();

        String num = generateNumbers();
        char char1 = alphabet.charAt(random.nextInt(12));
        char char2 = alphabet.charAt(random.nextInt(12));
        char char3 = alphabet.charAt(random.nextInt(12));

        return char1 + num + char2 + char3 + "116RUS";
    }

    private static String generateNumbers() {
        return ("" + (rnd.nextInt(1000) + 1000)).substring(1);
    }
}