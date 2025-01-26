package Automation.SeleniumAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;

import java.util.HashMap;

import org.openqa.selenium.*;

public class LambdaTestSeleniumScenario1 {

	WebDriver driver = null;
	String EnterMessage = "WelcometoLambdaTest";

	@Test
	public void scenario1() {

		WebElement SimpleFormDemoLink = driver.findElement(By.xpath("//a[contains(text(),'Simple Form Demo')]"));

		if (SimpleFormDemoLink.isDisplayed()) {
			SimpleFormDemoLink.click();

			// To validate the URL has "simple-form-demo"

			String URLValues = driver.getCurrentUrl();
			if (URLValues.contains("simple-form-demo")) {
				System.out.println("URL has the value simple-form-demo");

				WebElement enterMssg = driver.findElement(By.xpath("//input[@id='user-message']"));
				WebElement GetCheckedValueBtn = driver
						.findElement(By.xpath("//button[contains(text(),'Get Checked Value')]"));
				enterMssg.sendKeys(EnterMessage);
				GetCheckedValueBtn.click();

				WebElement mssg = driver
						.findElement(By.xpath("//label[contains(text(),'Your Message: ')]/parent::div//p"));
				if (mssg.isDisplayed()) {
					System.out.println("Message is displayed correctly");
				} else {
					throw new RuntimeException("Entered message is not displayed");
				}
			} else {
				throw new RuntimeException("Luks like launched to someother URL");
			}

		} else {
			System.out.println("SimpleFormDemoLink is not displayed");
			throw new RuntimeException("SimpleFormDemoLink is not displayed");
		}
	}

	@BeforeMethod
	public void beforeMethod() {

		/*
		 * System.setProperty("webdriver.edge.driver",
		 * "‪C:\\chromeDriver\\msedgedriver.exe"); WebDriver driver = new EdgeDriver();
		 */

		ChromeOptions browserOptions = new ChromeOptions();
		
		browserOptions.setCapability("browserVersion", "132.0");

		HashMap<String, Object> ltOptions = new HashMap<>();
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("build", "Assignment");
		ltOptions.put("project", "SeleniumProj");
		ltOptions.put("name", "LambdaTestSuite");
		ltOptions.put("console", "error");
		ltOptions.put("selenium_version", "4.11.0");
		browserOptions.setCapability("LT:Options", ltOptions);

		System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
		driver = new ChromeDriver(browserOptions);
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
