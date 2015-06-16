package com.softserveinc.edu.ita.utils;

import java.io.IOException;
import java.util.Random;

import static com.softserveinc.edu.ita.utils.PropertyLoader.getProperty;

public class StringsGenerator {

    /**
     * Random String generator
     *
     * @param validSymbolsString set of symbols for generate string in valid_symbols.prop
     * @param minimalLength      minimal length of returned string
     * @param maximalLength      maximal length of returned string
     */
    public static String generateString(String validSymbolsString, int minimalLength, int maximalLength) {

        final String SYMBOLS_FILENAME = "symbol_sets.properties";

        StringBuilder generatedString = new StringBuilder(maximalLength);
        Random randomGenerator = new Random();

        int generatedStringLength = minimalLength +
                randomGenerator.nextInt(maximalLength - minimalLength + 1);

        try {
            String validSymbols = getProperty(validSymbolsString, SYMBOLS_FILENAME);
            for (int i = 0; i < generatedStringLength; i++) {
                generatedString.append(validSymbols
                        .charAt(randomGenerator.nextInt(validSymbols.length())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return generatedString.toString();
    }

}
