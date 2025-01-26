package Automation.SeleniumAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;

public class LambdaTestSeleniumScenario2 {
	
	WebDriver driver = null;
	String EnterMessage = "WelcometoLambdaTest";
  
  @Test
  public void scenario2() {
	
	  WebElement dragAndDropSliders = driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop Sliders')]"));

		if (dragAndDropSliders.isDisplayed()) {
			dragAndDropSliders.click();

		WebElement slider =	driver.findElement(By.xpath("//h4[contains(text(),' Default value 15')]/parent::div//input"));
		 Actions actions = new Actions(driver);
         int width = slider.getSize().getWidth();
         int moveBy = (int) (width * 0.38);

         // Drag the slider
         actions.clickAndHold(slider)
                .moveByOffset(moveBy, 0) 
                .release()
                .perform();
		} else {
			System.out.println("SimpleFormDemoLink is not displayed");
			throw new RuntimeException("SimpleFormDemoLink is not displayed");
		}
  }
  @BeforeMethod
  public void beforeMethod() {
	 
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
