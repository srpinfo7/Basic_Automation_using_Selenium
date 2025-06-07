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

public class File16 {

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
			int totalLinkCount=driver.findElements(By.tagName("a")).size();
			System.out.println("Total links present in this page are "+totalLinkCount);
			WebElement headerDriver=driver.findElement(By.cssSelector(".jumbotron.text-center.header_style"));
			int headerLinkCount=headerDriver.findElements(By.tagName("a")).size();
			System.out.println("Total links present in header are "+headerLinkCount);
			
			for(int i=0;i<headerLinkCount;i++)
			{
				String openLinkInSeparateTab=Keys.chord(Keys.CONTROL,Keys.ENTER);
				headerDriver.findElements(By.tagName("a")).get(i).sendKeys(openLinkInSeparateTab);
			}
			
			Set<String> childWindows=driver.getWindowHandles();			
			Iterator<String> it=childWindows.iterator();
			String parent=it.next();
			while(it.hasNext())
			{
				driver.switchTo().window(it.next());
				String pageTitle=driver.getTitle();
				System.out.println(pageTitle);
				//driver.switchTo().window(parent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.sleep(5000);
			driver.quit();
		}

	}

	

	
}
