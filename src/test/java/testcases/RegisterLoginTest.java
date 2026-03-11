package testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import base.BaseTest;
import pages.RegisterPage;
import pages.LoginPage;

public class RegisterLoginTest extends BaseTest{
	RegisterPage registerPage;
	LoginPage loginPage;
	@BeforeMethod
	public void pageSetup() {
		registerPage = new RegisterPage(driver);
	    loginPage = new LoginPage(driver);
	}
	@DataProvider(name = "userData")
    public Object[][] getData() {
        return new Object[][]{
                {"Sushma", "sushma1@gmail.com", "Pass@123"},
                {"Ravi", "ravi3@gmail.com", "Pass@789"}
        };
    }
	
	@Test(dataProvider = "userData")
    public void registerAndLoginTest(String name, String email, String password) throws Exception {
    	registerPage.openRegisterForm();
        Thread.sleep(3000);
        registerPage.registerUser(name, email, password,
                "C:\\Users\\HP\\OneDrive\\Pictures\\user-icon-balanced.jpg");
        Thread.sleep(3000);
        loginPage.loginUser(email, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("index.html"));
    }

  
}