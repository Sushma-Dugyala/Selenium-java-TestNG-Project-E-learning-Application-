package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class VideoPage {
	WebDriver driver;
	WebDriverWait wait;
	public VideoPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	//locators
	@FindBy(id = "videoPlayer")
	WebElement videoPlayer;
	//Methods
	public boolean isVideoDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(videoPlayer));
		return videoPlayer.isDisplayed();
	}
	public void playVideo() {
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].muted = true;", videoPlayer);
		((JavascriptExecutor) driver)
		.executeScript("arguments[0].play();", videoPlayer);
	}
	public boolean isVideoPlaying() {
	    wait.until(ExpectedConditions.visibilityOf(videoPlayer));

	    return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].currentTime > 0 "
	    		+ "&& !arguments[0].paused && !arguments[0].ended;",
	                    videoPlayer);
	}
	//playback time
	public double getCurrentTime() {
		return (Double) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].currentTime;", videoPlayer);
	}
}