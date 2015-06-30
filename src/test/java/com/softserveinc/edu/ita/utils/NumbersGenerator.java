package com.softserveinc.edu.ita.utils;


import java.util.Random;

public class NumbersGenerator {

    public static int getRandomNumber(int number) {
        Random randomGenerator = new Random();
        int randomLoginRow = randomGenerator.nextInt(number) + 1;

        return randomLoginRow;
    }
}
