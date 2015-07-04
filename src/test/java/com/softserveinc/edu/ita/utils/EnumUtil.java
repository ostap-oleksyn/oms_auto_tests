package com.softserveinc.edu.ita.utils;


import java.util.Random;

/**
 * Class with enum util methods.
 */
public final class EnumUtil {

    private EnumUtil() {
    }

    /**
     * This method returns a random enum from an enum class passed into it.
     * Depending on the passed parameter, it can exclude enums starting
     * from the end of the enum list.
     *
     * @param className - name of the enum class
     * @param modifier  - limits the number of processed enums, no parameter - get random enum from all enums in class,
     *                  1 - get random enum from all enums in class, except the last one, 2 - except the last two, etc.
     */
    public static <T extends Enum> T getRandomEnum(final Class<T> className, final int... modifier) {
        final Random random = new Random();
        int enumsToExclude = 0;
        for (final int value : modifier) {
            enumsToExclude += value;
        }
        final int i = random.nextInt(className.getEnumConstants().length - enumsToExclude);
        return className.getEnumConstants()[i];
    }
}
