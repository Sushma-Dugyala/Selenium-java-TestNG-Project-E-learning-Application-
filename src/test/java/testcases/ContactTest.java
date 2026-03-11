package testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ContactPage;

import java.time.Duration;

public class ContactTest extends BaseTest {
	ContactPage contactPage;
	WebDriverWait wait;

	@BeforeMethod
	public void setupContact() {
		createAndLoginUser();
		System.out.println("URL after login: " + driver.getCurrentUrl());
		contactPage = new ContactPage(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		System.out.println("Page title: " + driver.getTitle());
	}
	//Blank Field Validation
	@Test(priority=1)
	public void verifyBlankValidationMessage() throws Exception {
		contactPage.openMenu();
		Thread.sleep(3000);
		contactPage.clickContact();
		Thread.sleep(1000);
		contactPage.clickSubmit();
		String validationMessage =
				contactPage.getValidationMessage(contactPage.getNameField());
		Assert.assertTrue(validationMessage.contains("Please fill out"),
				"Validation message not displayed for blank name field");
	}
	//Phone Number Defect (accepts single digit)
	@Test(priority=2)
	public void verifyPhoneNumberDefect() throws Exception {
		contactPage.openMenu();
		Thread.sleep(3000);
		contactPage.clickContact();
		contactPage.enterName("Sushma");
		Thread.sleep(2000);
		contactPage.enterEmail("Sushma1@gmail.com");
		Thread.sleep(2000);
		contactPage.enterNumber("8"); 
		Thread.sleep(2000);// single digit accepted
		contactPage.enterMessage("Hello");
		Thread.sleep(1000);
		contactPage.clickSubmit();
		wait.until(ExpectedConditions.urlContains("/contact"));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/contact"),
				"Contact form did not redirect as expected");
	}
	//Valid Submission
	@Test(priority=3)
	public void verifyContactSubmitRedirectsTo404() throws Exception {
		contactPage.openMenu();
		Thread.sleep(2000);
		contactPage.clickContact();
		Thread.sleep(3000);
		contactPage.enterName("Sushma");
		Thread.sleep(3000);
		contactPage.enterEmail("Sushma1@gmail.com");
		Thread.sleep(2000);
		contactPage.enterNumber("8930808008");
		Thread.sleep(2000);
		contactPage.enterMessage("Hi");
		Thread.sleep(2000);
		contactPage.clickSubmit();
		wait.until(ExpectedConditions.urlContains("/contact"));
		
		Assert.assertTrue(driver.getCurrentUrl().contains("/contact"));
		Assert.assertTrue(driver.getPageSource().contains("404"),
				"404 page not displayed after contact submission");
	}
}