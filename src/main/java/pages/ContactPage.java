package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class ContactPage {

	WebDriver driver;
	WebDriverWait wait;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	//Locators
	@FindBy(xpath = "//div[@id='menu-btn']")
	WebElement menuBtn;

	@FindBy(xpath = "//a//span[text()='contact us']")
	WebElement contactLink;

	@FindBy(xpath = "//h3[text()='get in touch']")
	WebElement pageTitle;

	@FindBy(xpath = "//input[@placeholder='enter your name']")
	WebElement nameField;

	@FindBy(xpath = "//input[@placeholder='enter your email']")
	WebElement emailField;

	@FindBy(xpath = "//input[@type='number']")
	WebElement numberField;

	@FindBy(xpath = "//textarea[@name='msg']")
	WebElement messageField;

	@FindBy(xpath = "//input[@name='submit']")
	WebElement submitBtn;

	//Navigation Methods

	public void openMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
		try {
			if (!contactLink.isDisplayed()) {
				menuBtn.click();
			}
		} catch (Exception e) {
			menuBtn.click();
		}
		wait.until(ExpectedConditions.visibilityOf(contactLink));
	}
	public void clickContact() {
		wait.until(ExpectedConditions.elementToBeClickable(contactLink));
		contactLink.click();
		wait.until(ExpectedConditions.urlContains("/contact"));
		// Adding extra wait to load page 
		wait.until(ExpectedConditions.visibilityOf(nameField));
	}
	public boolean isContactPageVisible() {
		return pageTitle.isDisplayed();
	}
	// Form Methods
	public void enterName(String name) {
		nameField.clear();
		nameField.sendKeys(name);
	}
	public void enterEmail(String email) {
		emailField.clear();
		emailField.sendKeys(email);
	}
	public void enterNumber(String number) {
		numberField.clear();
		numberField.sendKeys(number);
	}
	public void enterMessage(String msg) {
		messageField.clear();
		messageField.sendKeys(msg);
	}
	public void clickSubmit() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
		submitBtn.click();
	}
	//Validation msg
	public String getValidationMessage(WebElement element) {
		return element.getAttribute("validationMessage");
	}
	public WebElement getNameField() {
		return nameField;
	}
}