package Repo2;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File19 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--incognito"); // <== RUNS CHROME IN INCOGNITO MODE
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			WebElement input = driver.findElement(By.id("autocomplete"));
	        input.sendKeys("ind");
	        Thread.sleep(1000); // Wait for suggestions to load

	        Actions actions = new Actions(driver);

	        // Loop to navigate the dropdown
	        for (int i = 0; i < 10; i++) {
	            actions.sendKeys(Keys.ARROW_DOWN).perform();
	            Thread.sleep(500); // Wait for input box to update

	            String currentValue = input.getAttribute("value");
	            System.out.println("Current suggestion: " + currentValue);

	            if (currentValue.equalsIgnoreCase("India")) {
	                actions.sendKeys(Keys.ENTER).perform();
	                System.out.println("Selected: " + currentValue);
	                break;
	            }
	        }
		}
			

		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}

	}

	

	
}
