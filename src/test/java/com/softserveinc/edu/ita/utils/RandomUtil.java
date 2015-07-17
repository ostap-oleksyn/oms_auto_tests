package com.softserveinc.edu.ita.utils;

import java.io.IOException;
import java.util.Random;

/**
 * Class with random generation util methods.
 */
public final class RandomUtil {

    private RandomUtil() {
    }

    /**
     * Random String generator
     *
     * @param symbolsSet    set of symbols for generate string in valid_symbols.prop
     * @param minimalLength minimal length of returned string
     * @param maximalLength maximal length of returned string
     */
    public static String getRandomString(final String symbolsSet, final int minimalLength, final int maximalLength) {

        final String WORKSHEET_NAME = "SymbolSets";

        final StringBuilder generatedString = new StringBuilder(maximalLength);
        final Random randomGenerator = new Random();

        final int generatedStringLength = minimalLength +
                randomGenerator.nextInt(maximalLength - minimalLength + 1);

        String validSymbols = null;

        try {
            validSymbols = XlsFileReader.getColumnFromXlsSheet(WORKSHEET_NAME, symbolsSet).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < generatedStringLength; i++) {
            generatedString.append(validSymbols
                    .charAt(randomGenerator.nextInt(validSymbols.length())));
        }

        return generatedString.toString();
    }

    /**
     * Returns random integer in a specified range
     * @param min - range minimum value
     * @param max - range maximum value
     */
    public static int getRandomInteger(final int min, final int max){
        final Random random = new Random();
        return random.nextInt(max) + min;
    }
}
