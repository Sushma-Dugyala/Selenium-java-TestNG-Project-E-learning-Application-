package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class RegisterPage {
	WebDriver driver;
	WebDriverWait wait;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "user-btn")
	WebElement userIcon;

	@FindBy(xpath = "//a[@href='/register']")
	WebElement registerBtn;

	@FindBy(xpath = "//input[@placeholder='enter your name']")
	WebElement nameField;

	@FindBy(name = "email")
	WebElement emailField;

	@FindBy(name = "pass")
	WebElement passField;

	@FindBy(name = "c_pass")
	WebElement confirmPassField;

	@FindBy(xpath = "//input[@type='file']")
	WebElement fileUpload;

	@FindBy(xpath = "//input[@value='register new']")
	WebElement submitBtn;

	public void openRegisterForm() {
	    wait.until(ExpectedConditions.elementToBeClickable(userIcon)).click();
	    wait.until(ExpectedConditions.visibilityOf(registerBtn));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerBtn);
	    wait.until(ExpectedConditions.visibilityOf(nameField));
	}

	public String registerUser(String name, String email, String password, String imagePath) {
		nameField.clear();
		nameField.sendKeys(name);
		emailField.clear();
		emailField.sendKeys(email);
		passField.clear();
		passField.sendKeys(password);
		confirmPassField.clear();
		confirmPassField.sendKeys(password);
		fileUpload.sendKeys(imagePath);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
		submitBtn.click();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		return alertText;
	}
}
