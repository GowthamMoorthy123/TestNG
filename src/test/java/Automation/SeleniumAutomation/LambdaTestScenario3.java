package Automation.SeleniumAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LambdaTestScenario3 {

	WebDriver driver = null;
	String EnterMessage = "WelcometoLambdaTest";
	String MessgValidation = "Thanks for contacting us, we will get back to you shortly.";

	@Test
	public void scenario3() {

		String Name = "name";

		WebElement inputFormSubmit = driver.findElement(By.xpath("//a[contains(text(),'Input Form Submit')]"));

		if (inputFormSubmit.isDisplayed()) {
			inputFormSubmit.click();

			WebElement SubmitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
			SubmitButton.click();

			String sourcepage = driver.getPageSource();
			System.out.println(sourcepage);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String warningMessage = (String) js.executeScript("return arguments[0].innerText;",
					driver.findElement(By.id("name")));
			System.out.println("Warning Message: " + warningMessage);

			WebElement name = driver.findElement(By.id("name"));
			WebElement email = driver.findElement(By.id("inputEmail4"));
			WebElement pwd = driver.findElement(By.id("inputPassword4"));
			WebElement Company = driver.findElement(By.xpath("//input[@id='company']"));
			WebElement websitename = driver.findElement(By.xpath("//input[@id='websitename']"));
			WebElement city = driver.findElement(By.xpath("//input[@id='inputCity']"));
			WebElement address1 = driver.findElement(By.xpath("//input[@id='inputAddress1']"));
			WebElement address2 = driver.findElement(By.xpath("//input[@id='inputAddress2']"));
			WebElement state = driver.findElement(By.id("inputState"));
			WebElement zipCode = driver.findElement(By.id("inputZip"));

			name.sendKeys("Subha");
			email.sendKeys("subha_b@persistent.com");
			pwd.sendKeys("1234");
			Company.sendKeys("Persistent");
			websitename.sendKeys("www.subhaBalu.com");
			WebElement Country = driver.findElement(By.xpath("//select[@name='country']"));
			Select drpdwn = new Select(Country);
			// drpdwn.selectByValue("United States");
			drpdwn.selectByVisibleText("United States");
			city.sendKeys("PlainCity");
			address1.sendKeys("58874 Apricotway");
			address2.sendKeys("Near Dublin");
			state.sendKeys("OH");
			zipCode.sendKeys("45897");

			SubmitButton.click();
			WebElement mssg = driver.findElement(By.cssSelector("p[class='success-msg hidden']"));
			String validMssg = mssg.getText();
			Assert.assertEquals(validMssg, MessgValidation);

		} else {
			System.out.println("inputFormSubmit is not displayed");
			throw new RuntimeException("inputFormSubmit is not displayed");
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
