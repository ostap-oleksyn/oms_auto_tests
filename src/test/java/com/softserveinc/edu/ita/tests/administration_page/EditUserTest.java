package com.softserveinc.edu.ita.tests.administration_page;

import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.Regions;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.locators.UserInfoPageLocators;
import com.softserveinc.edu.ita.pageobjects.AdministrationPage;
import com.softserveinc.edu.ita.pageobjects.EditUserPage;
import com.softserveinc.edu.ita.pageobjects.HomePage;
import com.softserveinc.edu.ita.pageobjects.UserInfoPage;
import com.softserveinc.edu.ita.utils.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.EnumUtil;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.testng.annotations.Test;

/**
 * Class to test existing user editing functionality.
 */
public class EditUserTest extends TestRunner {

    @Test(dataProvider = "getUserEditData", dataProviderClass = DataProviders.class)
    public void testUserEditing(final String firstName, final String lastName, final String password, final String email) {

        HomePage homePage = new HomePage(driver);
        final User admin = DBUtility.getAdmin();
        UserInfoPage userInfoPage = homePage.logIn(admin.getLogin(), admin.getPassword());
        final AdministrationPage administrationPage = userInfoPage.clickAdministrationTab();

        final Regions region = EnumUtil.getRandomEnum(Regions.class, 1);

        final EditUserPage editUserPage = administrationPage.clickEditButton(RandomUtil.getRandomInteger(1, 4));
        final String editUserLogin = editUserPage.getUserLogin();

        editUserPage.fillEmailField(email)
                .fillFirstNameField(firstName)
                .fillLastNameField(lastName)
                .fillPasswordFields(password)
                .selectRegion(region)
                .selectMerchandiserRole();

        homePage = editUserPage.clickCreateButton().
                clickLogOutButton();

        final User editUser = DBUtility.getByLogin(editUserLogin);

        loggingSoftAssert.assertEquals(editUser.getEmail(), email, "User email is changed in database");
        loggingSoftAssert.assertEquals(editUser.getFirstName(), firstName, "User first name is changed in database");
        loggingSoftAssert.assertEquals(editUser.getLastName(), lastName, "User last name is changed in database");
        loggingSoftAssert.assertEquals(editUser.getRegionReference(), region.getRegionReference(), "User region is changed in database");
        loggingSoftAssert.assertEquals(editUser.getRoleReference(), Roles.MERCHANDISER.getRoleReference(), "User role is changed in database");

        userInfoPage = homePage.logIn(editUser.getLogin(), editUser.getPassword());

        loggingSoftAssert.assertTrue(homePage.getElementText(UserInfoPageLocators.FIRST_NAME_LABEL)
                .contains(firstName), "User first name is changed");
        loggingSoftAssert.assertTrue(homePage.getElementText(UserInfoPageLocators.LAST_NAME_LABEL)
                .contains(lastName), "User last name is changed");
        loggingSoftAssert.assertEquals(homePage.getElementText(UserInfoPageLocators.USER_ROLE_LABEL),
                Roles.MERCHANDISER.getRoleName(), "User role is changed");

        loggingSoftAssert.assertAll();

        userInfoPage.clickLogOutButton();
    }
}
