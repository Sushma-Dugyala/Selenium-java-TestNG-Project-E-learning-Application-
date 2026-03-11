package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    SearchPage searchPage;

    @BeforeMethod
    public void setupSearch() {
        createAndLoginUser();
        searchPage = new SearchPage(driver);
    }

 
    @Test
    public void verifySearchFunctionality() {
        searchPage.enterSearchText("HTML");
        searchPage.clickSearch();

        Assert.assertTrue(searchPage.is404Displayed(),
                "Search did not redirect to 404 page as expected");
    }
}