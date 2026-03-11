package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Search box
    @FindBy(name = "search_box")
    WebElement searchBox;

    // Search button
    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchBtn;

    //Methods

    public void enterSearchText(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn));
        searchBtn.click();
    }

    public boolean is404Displayed() {
        try {
            // Wait until the body contains 404 text
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Page not found')]")
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    }
