package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='enter your email']")
	WebElement emailField;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passField;

	@FindBy(xpath = "//input[@value='login new']")
	WebElement loginBtn;

	public String loginUser(String email, String password) {

		wait.until(ExpectedConditions.visibilityOf(emailField));

		emailField.clear();
		emailField.sendKeys(email);

		passField.clear();
		passField.sendKeys(password);

		loginBtn.click();

		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();

		return alertText;
	}
}