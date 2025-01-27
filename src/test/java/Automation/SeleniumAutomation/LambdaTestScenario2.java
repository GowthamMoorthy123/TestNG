package Automation.SeleniumAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;

public class LambdaTestScenario2 {

	WebDriver driver = null;
	String EnterMessage = "WelcometoLambdaTest";

	@Test
	public void scenario2() {

		WebElement dragAndDropSliders = driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop Sliders')]"));

		if (dragAndDropSliders.isDisplayed()) {
			dragAndDropSliders.click();

			WebElement slider = driver
					.findElement(By.xpath("//h4[contains(text(),' Default value 15')]/parent::div//input"));
			Actions actions = new Actions(driver);
			int width = slider.getSize().getWidth();
			int moveBy = (int) (width * 0.38);

			// Drag the slider
			actions.clickAndHold(slider).moveByOffset(moveBy, 0).release().perform();
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

		ChromeOptions browserOptions = new ChromeOptions();
		// browserOptions.setPlatformName("Windows 11");
		browserOptions.setBrowserVersion("132");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "subhashini_b");
		ltOptions.put("accessKey", "Z2eM9fQt4qolS26WnmoZlMi2N77HvZAmL3WoJt5Pa3KV0YViUV");
		ltOptions.put("project", "LambdaTest");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		browserOptions.setCapability("LT:Options", ltOptions);

		try {
			URI uri = new URI(gridUrl);
			URL url = uri.toURL();
			driver = new RemoteWebDriver(url, browserOptions);
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
