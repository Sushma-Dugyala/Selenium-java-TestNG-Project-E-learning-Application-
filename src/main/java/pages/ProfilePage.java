package pages; 
import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy; 
import org.openqa.selenium.support.PageFactory; 
import org.openqa.selenium.support.ui.ExpectedConditions; 
import org.openqa.selenium.support.ui.WebDriverWait; 

public class ProfilePage { 
	WebDriver driver; 
	public ProfilePage(WebDriver driver) { 
		this.driver = driver; PageFactory.initElements(driver, this); 
	}
	@FindBy(xpath = "//a[@href='/profile']")
	WebElement viewProfileBtn;
	@FindBy(xpath = "//h3[@id='profile-username']")
	WebElement profileName;
	@FindBy(xpath = "//div//a[text()='update profile']") 
	WebElement updateProfileBtn; 
	@FindBy(xpath = "//h3[text()='update profile']") 
	WebElement updatePageTitle;
	@FindBy(xpath = "//input[@id='update-name']")
	WebElement nameField;
	@FindBy(xpath = "//input[@placeholder='Your Email']") 
	WebElement emailField; 
	@FindBy(name = "old_pass")
	WebElement oldPasswordField;
	@FindBy(name = "new_pass") 
	WebElement newPasswordField; 
	@FindBy(name = "c_pass") 
	WebElement confirmPasswordField;
	@FindBy(xpath = "//input[@type='file']")
	WebElement imageUpload; 
	@FindBy(name = "submit") 
	WebElement submitBtn; 
	//Navigation Methods
	public void clickViewProfile() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait for profile button 
		wait.until(ExpectedConditions.visibilityOf(viewProfileBtn));
		// Wait for viewprofile clickable
		wait.until(ExpectedConditions.elementToBeClickable(viewProfileBtn));
		//its scroll to center 
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].scrollIntoView({block:'center'});", viewProfileBtn);
		try {
			viewProfileBtn.click();
		} catch (ElementClickInterceptedException e) {
			// Fallback JS click if overlap elements
			((JavascriptExecutor) driver)
			.executeScript("arguments[0].click();", viewProfileBtn);
		}
	}
	public void clickUpdateProfile() { 
		updateProfileBtn.click(); 
	}
	public boolean isProfileNameVisible() {
		return profileName.isDisplayed(); 
	}
	public boolean isUpdatePageVisible() { 
		return updatePageTitle.isDisplayed();
	} 
}