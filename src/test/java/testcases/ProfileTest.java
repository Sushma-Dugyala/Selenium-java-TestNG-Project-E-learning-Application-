package testcases; 

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//
//import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProfilePage;

import java.time.Duration;

public class ProfileTest extends BaseTest {

	LoginPage loginPage;
	ProfilePage profilePage;
	WebDriverWait wait;

	@BeforeMethod
	public void setupProfile() {
	    createAndLoginUser();
	    profilePage = new ProfilePage(driver);
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	@Test(priority=1)
	public void verifyProfilePageNavigation() {

		// Click user icon first
		driver.findElement(By.id("user-btn")).click();
		profilePage.clickViewProfile();
		wait.until(ExpectedConditions.urlContains("/profile"));
		Assert.assertTrue(profilePage.isProfileNameVisible(),
				"Profile page did not load correctly");
	}

	@Test(priority=2)
	public void verifyUpdateProfilePageNavigation() throws Exception {
	    driver.findElement(By.id("user-btn")).click();
	    Thread.sleep(2000);
	    profilePage.clickViewProfile();
	    wait.until(ExpectedConditions.urlContains("/profile"));
	    profilePage.clickUpdateProfile();
	    wait.until(ExpectedConditions.urlContains("/update"));
	    
	    // Fill update form
	    driver.findElement(By.id("update-name")).clear();
	    driver.findElement(By.id("update-name")).sendKeys("Sai");
	    Thread.sleep(3000);
	    driver.findElement(By.name("old_pass")).sendKeys("Pass@123");
	    Thread.sleep(3000);
	    driver.findElement(By.name("new_pass")).sendKeys("NewPass@123");
	    Thread.sleep(3000);
	    driver.findElement(By.name("c_pass")).sendKeys("NewPass@123");
	    Thread.sleep(1000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement submitBtn = driver.findElement(By.name("submit"));

	    js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);

	    driver.findElement(By.xpath("//input[@type='file']"))
	            .sendKeys("C:\\Users\\HP\\OneDrive\\Pictures\\user-icon-balanced.jpg");
	    Thread.sleep(1000);
	    driver.findElement(By.name("submit")).click();
	    Thread.sleep(2000);
	    //page error 404 shows
	    wait.until(ExpectedConditions.urlContains("/update"));
	    Assert.assertTrue(driver.getCurrentUrl().contains("/update"));
	    Assert.assertTrue(driver.getPageSource().contains("404"),
	            "404 page not displayed after submit");
	}
} 