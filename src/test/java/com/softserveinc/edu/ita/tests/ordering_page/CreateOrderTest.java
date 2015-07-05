package com.softserveinc.edu.ita.tests.ordering_page;

import com.softserveinc.edu.ita.domains.Order;
import com.softserveinc.edu.ita.domains.Product;
import com.softserveinc.edu.ita.domains.User;
import com.softserveinc.edu.ita.enums.DimensionMultipliers;
import com.softserveinc.edu.ita.enums.Roles;
import com.softserveinc.edu.ita.pageobjects.*;
import com.softserveinc.edu.ita.tests.TestRunner;
import com.softserveinc.edu.ita.utils.DBUtility;
import com.softserveinc.edu.ita.utils.DataProviders;
import com.softserveinc.edu.ita.utils.RandomUtil;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.softserveinc.edu.ita.pageobjects.AddItemPage.SearchMethods.SEARCH_BY_DESCRIPTION;
import static com.softserveinc.edu.ita.pageobjects.AddItemPage.SortFields.SORT_BY_DESCRIPTION;
import static com.softserveinc.edu.ita.pageobjects.AddItemPage.SortFields.SORT_BY_NAME;
import static com.softserveinc.edu.ita.pageobjects.AddItemPage.SortType.ASC;
import static com.softserveinc.edu.ita.pageobjects.AddItemPage.SortType.DESC;


/**
 * Test creating of new Order (Ticket IFAA-20)
 */
public class CreateOrderTest extends TestRunner {

    @Test
    public void testCreateOrder() {

        final int ITEMS_COUNT = 3;
        final int MAX_RANDOM_ITEM_QUANTITY = 20;
        final String CVV2 = "123";

        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();
        loggingAssert.assertTrue(newOrderPage.isItemSelectionSectionPresent(), "Item selection section is present");

        // add random items to order
        for (int i = 0; i < ITEMS_COUNT; i++) {
            AddItemPage addItemPage = newOrderPage.clickAddItemButton();
            newOrderPage = addItemPage.clickRandomSelectItemLink()
                    .fillRandomQuantity(MAX_RANDOM_ITEM_QUANTITY)
                    .selectRandomDimension()
                    .clickDoneButton();
        }

        // fill order fields
        newOrderPage.selectRandomAssignee()
                .fillRandomCreditCardNumber()
                .fillCVV2NumberField(CVV2)
                .fillRandomPreferableDeliveryDate()
                .clickSaveButton();

        final Order newOrder = DBUtility.getByOrderNumber(newOrderPage.getOrderNumber());
        loggingAssert.assertNotNull(newOrder, "The order is created in database");
        loggingAssert.assertEquals(ITEMS_COUNT, DBUtility.getOrderItemsCount(newOrder),
                "Order items are created in database");

        // clean database
        DBUtility.deleteOrderItems(newOrder);
        DBUtility.deleteOrder(newOrder);

        userInfoPage.clickLogOutButton();
    }

    @Test
    public void testAvailableItemsCount() {
        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();
        AddItemPage addItemPage = newOrderPage.clickAddItemButton();

        loggingAssert.assertEquals(addItemPage.getItemsCount(), DBUtility.getActiveProductsCount(),
                "Displayed items count equals a count in database");

        addItemPage.clickLogOutButton();
    }

    @Test(dataProvider = "products", dataProviderClass = DataProviders.class)
    public void testItemsSearching(String productName, String productDescription) {
        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());

        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        final NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();
        final AddItemPage addItemPage = newOrderPage.clickAddItemButton();

        addItemPage.fillSearchInput(productName).clickSearchButton();

        List<String[]> searchedItemsList = addItemPage.getItemsTable();
        for (String[] item : searchedItemsList) {
            loggingAssert.assertTrue(item[0].startsWith(productName),
                    "Product name equals a searched text");
        }

        searchedItemsList = addItemPage
                .selectSearchMethod(SEARCH_BY_DESCRIPTION)
                .fillSearchInput(productDescription)
                .clickSearchButton()
                .getItemsTable();

        for (String[] item : searchedItemsList) {
            loggingAssert.assertTrue(item[1].startsWith(productDescription),
                    "Product description equals a searched text");
        }

        addItemPage.clickLogOutButton();
    }

    @Test
    public void testNotValidSearching() {
        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());

        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        final NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();
        final AddItemPage addItemPage = newOrderPage.clickAddItemButton();

        addItemPage.fillSearchInput(RandomUtil.getRandomString("Digits", 20, 40));
        addItemPage.clickSearchButton();
        loggingAssert.assertEquals(0, addItemPage.getItemsTable().size(), "Nothing is returned after searching");

        addItemPage.clickLogOutButton();
    }

    @Test
    public void testOrderNavigation() {
        final int ITEMS_COUNT = 10;
        final int MAX_RANDOM_ITEM_QUANTITY = 20;
        int ITEMS_PER_PAGE = 25;

        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();

        // add random 10 items to order - navigate buttons must be disabled
        for (int i = 0; i < ITEMS_COUNT; i++) {
            AddItemPage addItemPage = newOrderPage.clickAddItemButton();
            newOrderPage = addItemPage.clickRandomSelectItemLink()
                    .fillRandomQuantity(MAX_RANDOM_ITEM_QUANTITY)
                    .selectRandomDimension()
                    .clickDoneButton();

            loggingSoftAssert.assertFalse(newOrderPage.isFirstButtonEnabled(), "First button is enabled");
            loggingSoftAssert.assertFalse(newOrderPage.isBackwardButtonEnabled(), "Backward button is enabled");
            loggingSoftAssert.assertFalse(newOrderPage.isForwardButtonEnabled(), "Forward button is enabled");
            loggingSoftAssert.assertFalse(newOrderPage.isLastButtonEnabled(), "Last button is enabled");
        }

        // add random 20 items to order - first and forward buttons must be disabled,
        // last and backward buttons - enabled.
        for (int i = 0; i < ITEMS_COUNT * 2; i++) {
            AddItemPage addItemPage = newOrderPage.clickAddItemButton();
            newOrderPage = addItemPage.clickRandomSelectItemLink()
                    .fillRandomQuantity(MAX_RANDOM_ITEM_QUANTITY)
                    .selectRandomDimension()
                    .clickDoneButton();

            loggingSoftAssert.assertFalse(newOrderPage.isFirstButtonEnabled(), "First button is enabled");
            loggingSoftAssert.assertFalse(newOrderPage.isBackwardButtonEnabled(), "Backward button is enabled");
            loggingSoftAssert.assertTrue(newOrderPage.isForwardButtonEnabled(), "Forward button is enabled");
            loggingSoftAssert.assertTrue(newOrderPage.isLastButtonEnabled(), "Last button is enabled");
        }

        // click forward button - all buttons must be enabled
        newOrderPage.clickForwardButton();
        loggingSoftAssert.assertTrue(newOrderPage.isFirstButtonEnabled(), "First button is enabled");
        loggingSoftAssert.assertTrue(newOrderPage.isBackwardButtonEnabled(), "Backward button is enabled");
        loggingSoftAssert.assertTrue(newOrderPage.isForwardButtonEnabled(), "Forward button is enabled");
        loggingSoftAssert.assertTrue(newOrderPage.isLastButtonEnabled(), "Last button is enabled");

        // click last button - first and forward buttons must be enabled,
        // last and backward buttons - disabled.
        newOrderPage.clickForwardButton();
        loggingSoftAssert.assertTrue(newOrderPage.isFirstButtonEnabled(), "First button is enabled");
        loggingSoftAssert.assertTrue(newOrderPage.isBackwardButtonEnabled(), "Backward button is enabled");
        loggingSoftAssert.assertFalse(newOrderPage.isForwardButtonEnabled(), "Forward button is enabled");
        loggingSoftAssert.assertFalse(newOrderPage.isLastButtonEnabled(), "Last button is enabled");

        loggingSoftAssert.assertAll();

        // show 25 items per page
        newOrderPage.selectShowLinesCount(1);
        loggingAssert.assertEquals(ITEMS_PER_PAGE, newOrderPage.getItemsTable().size(),
                "Displayed correct items count");

        // show 10 items per page
        ITEMS_PER_PAGE = 10;
        newOrderPage.selectShowLinesCount(0);
        loggingAssert.assertEquals(ITEMS_PER_PAGE, newOrderPage.getItemsTable().size(),
                "Displayed correct items count");

        newOrderPage.clickLogOutButton();
    }


    @Test
    public void testDisplayedSums() {

        final int ITEMS_COUNT = 3;
        final int MAX_RANDOM_ITEM_QUANTITY = 20;

        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();

        // add random items to order
        for (int i = 0; i < ITEMS_COUNT; i++) {
            AddItemPage addItemPage = newOrderPage.clickAddItemButton();
            newOrderPage = addItemPage.clickRandomSelectItemLink()
                    .fillRandomQuantity(MAX_RANDOM_ITEM_QUANTITY)
                    .selectRandomDimension()
                    .clickDoneButton();
        }

        // displayed sums
        List<String[]> itemsList = newOrderPage.getItemsTable();
        BigDecimal totalPrice = new BigDecimal(0);
        BigDecimal itemPrice, itemsCount, itemSum, dimensionMultiplier;

        for (String[] item : itemsList) {
            itemPrice = new BigDecimal(item[4]);
            itemsCount = new BigDecimal(item[5]);
            dimensionMultiplier = new BigDecimal(DimensionMultipliers.getItemsCount(item[3]));
            itemSum = itemPrice.multiply(itemsCount).multiply(dimensionMultiplier);
            totalPrice = totalPrice.add(itemSum);
        }

        loggingAssert.assertEquals(totalPrice.doubleValue(), newOrderPage.getTotalPrice(),
                "Calculated and displayed total prices are equal");

        userInfoPage.clickLogOutButton();
    }

    @Test
    public void testItemsEditing() {

        final int MAX_RANDOM_ITEM_QUANTITY = 20;

        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();

        NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();

        // add first item to order
        AddItemPage addItemPage = newOrderPage.clickAddItemButton();
        newOrderPage = addItemPage.clickSelectItemLink(1)
                .fillRandomQuantity(MAX_RANDOM_ITEM_QUANTITY)
                .selectRandomDimension()
                .clickDoneButton();

        String[] firstItem = newOrderPage.getItemsTable().get(0);

        addItemPage = newOrderPage.clickEditLink();
        newOrderPage = addItemPage.clickSelectItemLink(2)
                .fillRandomQuantity(MAX_RANDOM_ITEM_QUANTITY)
                .selectRandomDimension()
                .clickDoneButton();

        String[] secondItem = newOrderPage.getItemsTable().get(0);

        loggingAssert.assertNotEquals(firstItem[0], secondItem[0]);

        newOrderPage.clickLogOutButton();
    }

    @Test
    public void testAddItemSorting() {

        final HomePage homePage = new HomePage(driver);
        final User customer = DBUtility.getByRole(Roles.CUSTOMER);
        final UserInfoPage userInfoPage = homePage.logIn(customer.getLogin(), customer.getPassword());
        final OrderingPage orderingPage = userInfoPage.clickOrderingTab();
        final NewOrderPage newOrderPage = orderingPage.clickCreateNewOrderLink();
        final AddItemPage addItemPage = newOrderPage.clickAddItemButton();

        List<Product> productsList = DBUtility.getActiveProducts();
        List<String[]> itemsList;

        // test sorting by name ascending
        itemsList = addItemPage.getItemsTable();
        addItemPage.sortProductList(productsList, SORT_BY_NAME, ASC);
        loggingSoftAssert.assertTrue(addItemPage.isItemsListSorted(itemsList, productsList),
                "Items are sorted by name in ascending order");

        // test sorting by name descending
        addItemPage.clickSortLink(SORT_BY_NAME);
        itemsList = addItemPage.getItemsTable();
        addItemPage.sortProductList(productsList, SORT_BY_NAME, DESC);
        loggingSoftAssert.assertTrue(addItemPage.isItemsListSorted(itemsList, productsList),
                "Items are sorted by name in descending order");

        // test sorting by description ascending
        addItemPage.clickSortLink(SORT_BY_DESCRIPTION);
        itemsList = addItemPage.getItemsTable();
        addItemPage.sortProductList(productsList, SORT_BY_DESCRIPTION, ASC);
        loggingSoftAssert.assertTrue(addItemPage.isItemsListSorted(itemsList, productsList),
                "Items are sorted by description in ascending order");

        // test sorting by description descending
        addItemPage.clickSortLink(SORT_BY_DESCRIPTION);
        itemsList = addItemPage.getItemsTable();
        addItemPage.sortProductList(productsList, SORT_BY_DESCRIPTION, DESC);
        loggingSoftAssert.assertTrue(addItemPage.isItemsListSorted(itemsList, productsList),
                "Items are sorted by description in descending order");

        loggingSoftAssert.assertAll();
        addItemPage.clickLogOutButton();
    }
}
