package com.softserveinc.edu.ita.utils;

import java.io.IOException;
import java.util.Random;

import static com.softserveinc.edu.ita.utils.PropertyLoader.getProperty;

public class StringsGenerator {

    /**
     * Random String generator
     *
     * @param symbolsSet set of symbols for generate string in valid_symbols.prop
     * @param minimalLength      minimal length of returned string
     * @param maximalLength      maximal length of returned string
     */
    public static String generateString(String symbolsSet, int minimalLength, int maximalLength) {

        //final String SYMBOLS_FILENAME = "symbol_sets.properties";
        final String WORKSHEET_NAME = "SymbolSets";

        StringBuilder generatedString = new StringBuilder(maximalLength);
        Random randomGenerator = new Random();

        int generatedStringLength = minimalLength +
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

}
