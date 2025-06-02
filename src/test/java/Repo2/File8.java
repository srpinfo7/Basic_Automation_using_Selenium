package Repo2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File8 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		WebDriver driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.get("http://spicejet.com/");
		Thread.sleep(1000);
		
//		System.out.println(driver.findElement(By.cssSelector("div[data-testid*=\"departure-date\"]")).getDomAttribute("style"));
//		System.out.println(driver.findElement(By.cssSelector("div[data-testid*=\"return-date\"]")).getDomAttribute("style"));
		
		checkReturnDateDisabled(driver);
		
		driver.findElement(By.cssSelector("div[data-testid=\"round-trip-radio-button\"]")).click();
		Thread.sleep(1000);
		
		checkReturnDateEnabled(driver);
		
//		System.out.println(driver.findElement(By.cssSelector("div[data-testid*=\"departure-date\"]")).getDomAttribute("style"));
//		System.out.println(driver.findElement(By.cssSelector("div[data-testid*=\"return-date\"]")).getDomAttribute("style"));
//		
		driver.quit();
	}
	
	public static void checkReturnDateDisabled(WebDriver driver)
	{
		if(driver.findElement(By.cssSelector("div[data-testid*=\"return-date\"]")).getDomAttribute("style").contains("rgb"))
		{
			System.out.println("Deparature date is disabled");
			Assert.assertTrue(true);
		}
		else
		{
			System.out.println("Deparature date is enabled");
			Assert.assertTrue(false);
		}
	}

	
	public static void checkReturnDateEnabled(WebDriver driver)
	{
		if(!driver.findElement(By.cssSelector("div[data-testid*=\"return-date\"]")).getDomAttribute("style").contains("rgb"))
		{
			System.out.println("Deparature date is Enabled");
			Assert.assertTrue(true);
		}
		else
		{
			System.out.println("Deparature date is Disabled");
			Assert.assertTrue(false);
		}
	}
}
