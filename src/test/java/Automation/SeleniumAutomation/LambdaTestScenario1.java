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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.*;

public class LambdaTestScenario1 {

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

		String username = "subhashini_b";
		String accessKey = "Z2eM9fQt4qolS26WnmoZlMi2N77HvZAmL3WoJt5Pa3KV0YViUV";
		String gridUrl = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

		//ChromeOptions browserOptions = new ChromeOptions();
		// browserOptions.setPlatformName("Windows 11");
		FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBrowserVersion("134.0");
		//browserOptions.setBrowserVersion("132");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "subhashini_b");
		ltOptions.put("accessKey", "Z2eM9fQt4qolS26WnmoZlMi2N77HvZAmL3WoJt5Pa3KV0YViUV");
		ltOptions.put("project", "LambdaTest");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		firefoxOptions.setCapability("LT:Options", ltOptions);

		try {
			URI uri = new URI(gridUrl);
			URL url = uri.toURL();
			driver = new RemoteWebDriver(url, firefoxOptions);
		} catch (URISyntaxException | MalformedURLException e) {
			// Handle the exception appropriately
			System.err.println("Error constructing URL: " + e.getMessage());
		}

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
