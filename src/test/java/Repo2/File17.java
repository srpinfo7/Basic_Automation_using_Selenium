package Repo2;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
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

public class File17 {

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
			String monthNumber = "9";
			String date="20";
			String year="2027";
			
			String actualDate[]= {monthNumber,date,year};
			
		

			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
			driver.findElement(By.className("react-date-picker__inputGroup")).click();
			driver.findElement(By.className("react-calendar__navigation__label")).click();
			driver.findElement(By.className("react-calendar__navigation__label")).click();
			driver.findElement(By.xpath("//button[text()='" + year + "']")).click();
			driver.findElement(By.cssSelector(".react-calendar__year-view__months__month:nth-child("+monthNumber+")")).click();
			driver.findElement(By.xpath("//abbr[text()='"+date+"']")).click();
			
			List<WebElement> dateValue=driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));
			for(int i=0;i<dateValue.size();i++)
			{
				System.out.print(dateValue.get(i).getAttribute("value")+" ");
				Assert.assertEquals(actualDate[i], dateValue.get(i).getAttribute("value"));
				
			}
			
						

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}

	}

	

	
}
