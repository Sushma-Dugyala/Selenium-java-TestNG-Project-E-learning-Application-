package factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class DriverFactory {
	public static WebDriver initDriver() {
		WebDriver driver = null;
		String browser = ConfigReader.get("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
}
