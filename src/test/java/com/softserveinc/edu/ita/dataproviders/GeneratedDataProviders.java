package com.softserveinc.edu.ita.dataproviders;

import com.softserveinc.edu.ita.dao_jdbc.domains.User;
import com.softserveinc.edu.ita.enums.Regions;
import com.softserveinc.edu.ita.enums.Roles;
import org.testng.annotations.DataProvider;


import static com.softserveinc.edu.ita.utils.StringsGenerator.generateString;

/**
 *
 */
public class GeneratedDataProviders {

    @DataProvider(name="generatedValidUserData")
    public static Object[][] generateValidUserData() {

        final int GENERATED_USERS_COUNT = 5;

        Object[][] usersInfoData = new Object[GENERATED_USERS_COUNT][1];

        for (int i=0; i < GENERATED_USERS_COUNT; i++) {
            User user = new User();

            user.setLogin(generateString("name_symbols", 5, 13).toLowerCase());
            user.setLastName(generateString("name_symbols", 5, 13).toLowerCase());
            user.setFirstName(generateString("name_symbols", 5, 13).toLowerCase());
            user.setPassword(generateString("password_symbols", 4, 10));
            user.setEmail(generateString("email_symbols", 4, 8) + "@"
                    + generateString("domain_names_symbols", 4, 8) + "."
                    + generateString("domain_names_symbols", 3, 4));
            user.setRegionName(String.valueOf(Regions.getRandomRegion()));
            user.setRoleName(String.valueOf(Roles.getRandomRole()));

            usersInfoData[i][0] = user;
        }

        return usersInfoData;
    }

}
