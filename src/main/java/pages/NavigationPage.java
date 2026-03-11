package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class NavigationPage {

	WebDriver driver;
	WebDriverWait wait;

	public NavigationPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Locators
	@FindBy(id = "menu-btn")
	WebElement menuBtn;

	@FindBy(xpath = "//span[text()='about']")
	WebElement aboutBtn;

	@FindBy(xpath = "//div//h3[text()='why choose us?']")
	WebElement aboutText;

	@FindBy(xpath = "//a[@href='/courses']")
	WebElement coursesBtn;

	@FindBy(xpath = "//a[@href='/playlist']")
	WebElement playlistBtn;

	@FindBy(xpath = "//h1[text()='playlist details']")
	WebElement playlistTitle;

	@FindBy(xpath = "//img[@id='pv1']")
	WebElement firstVideo;

	@FindBy(xpath = "//video[@src='images/vid-1.mp4']")
	WebElement videoPlayer;

	@FindBy(xpath = "//span[text()='teachers']")
	WebElement teachersBtn;

	@FindBy(xpath = "//h1[text()='expert teachers']")
	WebElement teachersTitle;

	//Methods
	public void openMenu() {
	    wait.until(ExpectedConditions.elementToBeClickable(menuBtn));
	    menuBtn.click();

	    try {
	        // Wait 3s for a nav item to appear
	        new WebDriverWait(driver, Duration.ofSeconds(3))
	            .until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//span[text()='about']")));
	    } catch (TimeoutException e) {
	        // Menu closed — click again to reopen
	        menuBtn.click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[text()='about']")));
	    }
	}
	public void goToAbout() {
		openMenu();
		wait.until(ExpectedConditions.elementToBeClickable(aboutBtn));
		aboutBtn.click();
		wait.until(ExpectedConditions.visibilityOf(aboutText));
	}
	public boolean isAboutPageLoaded() {
		return aboutText.isDisplayed();
	}
	public void goToCourses() {
		openMenu();   //Ensure sidebar is open
		wait.until(ExpectedConditions.elementToBeClickable(coursesBtn));
		coursesBtn.click();
		wait.until(ExpectedConditions.urlContains("/courses"));
	}
	public void openPlaylist() {
		wait.until(ExpectedConditions.elementToBeClickable(playlistBtn));
		playlistBtn.click();
		wait.until(ExpectedConditions.visibilityOf(playlistTitle));
	}
	public boolean isPlaylistLoaded() {
		return playlistTitle.isDisplayed();
	}
	public void openFirstVideo() {
		wait.until(ExpectedConditions.visibilityOf(firstVideo));
		// Scroll to video
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].scrollIntoView({block: 'center'});", firstVideo);
		wait.until(ExpectedConditions.elementToBeClickable(firstVideo));
		firstVideo.click();
		wait.until(ExpectedConditions.urlContains("/watch-video"));
	}
	public boolean isVideoDisplayed() {
		return videoPlayer.isDisplayed();
	}
	public void goToTeachers() {
		openMenu();   //Always open before clicking
		wait.until(ExpectedConditions.elementToBeClickable(teachersBtn));
		teachersBtn.click();
		wait.until(ExpectedConditions.visibilityOf(teachersTitle));
	}
	public boolean isTeachersPageLoaded() {
		return teachersTitle.isDisplayed();
	}
}
