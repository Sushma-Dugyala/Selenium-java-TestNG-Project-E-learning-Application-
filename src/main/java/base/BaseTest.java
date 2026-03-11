package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import factory.DriverFactory;
import pages.LoginPage;
import pages.RegisterPage;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    private static final String Name = "Sushma";
    private static final String PASSWORD = "Pass@123";
    private static final String IMAGE_PATH = "C:\\Users\\HP\\OneDrive\\Pictures\\user-icon-balanced.jpg";

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(
                Integer.parseInt(ConfigReader.get("implicitWait"))
            )
        );
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void createAndLoginUser() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        //unique email every time because site uses local storage
        String uniqueEmail = "sushma" + System.currentTimeMillis() + "@gmail.com";

        registerPage.openRegisterForm();
        registerPage.registerUser(Name, uniqueEmail, PASSWORD, IMAGE_PATH);

        loginPage.loginUser(uniqueEmail, PASSWORD);
        System.out.println("Registered and logged in with: " + uniqueEmail);
    }
}