package com.softserveinc.edu.ita.utils;


import java.util.Random;

public final class EnumUtil {

    private EnumUtil() {
    }

    /**
     * This method returns a random enum from an enum class passed into it.
     *
     * @param className - name of the enum class
     * @param modifier  - limits the number of proccesed enums, 0 - get random enum from all enums in class,
     *                  1 - get random enum from all enums in class, except the last one, etc.
     */
    public static <T extends Enum> T getRandomEnum(Class<T> className, int... modifier) {
        final Random random = new Random();
        int mod = 0;
        for (int value : modifier) {
            mod += value;
        }
        final int i = random.nextInt(className.getEnumConstants().length - mod);
        return className.getEnumConstants()[i];
    }
}
