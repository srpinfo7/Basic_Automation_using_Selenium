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

public class File18 {

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
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[style*='overflow-y']")));
			js.executeScript("document.querySelector(\"div[style*='overflow-y']\").scrollTop = 100");
			
			List<WebElement> fourthColumnCells = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
			int sum=0;
			for (WebElement cell : fourthColumnCells) {
			    System.out.println("4th column value: " + cell.getText());
			    sum=sum+Integer.parseInt(cell.getText());
			    
			}
			System.out.println("Sum of all Amount " + sum);					
			String totalText=driver.findElement(By.className("totalAmount")).getText();
			int amount=Integer.parseInt(totalText.split(":")[1].trim());
			
			System.out.println("Total Amount is "+amount);
			Assert.assertEquals(sum, amount);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}

	}

	

	
}
