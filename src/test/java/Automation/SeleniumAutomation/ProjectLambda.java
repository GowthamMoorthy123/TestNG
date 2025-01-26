package Automation.SeleniumAutomation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProjectLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");

		String EnterMessage = "WelcometoLambdaTest";
		WebDriver driver = new ChromeDriver();

		// To Launch URL
		driver.get("https://www.lambdatest.com/selenium-playground/");

		// To get Current URL
		String URL = driver.getCurrentUrl();
		System.out.println("URL is - " + URL);

		// To validate the URL has simple-form-demo

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

}
