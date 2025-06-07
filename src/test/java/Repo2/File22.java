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

public class File22 {

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

			driver.get("https://www.amazon.com/");
			int totalLinkCount=driver.findElements(By.tagName("a")).size();
			System.out.println("Total links present in this page are "+totalLinkCount);
			WebElement footerDriver=driver.findElement(By.cssSelector(".navFooterVerticalRow.navAccessibility"));
			int footerLinkCount=footerDriver.findElements(By.tagName("a")).size();
			List<WebElement> footerLinks=footerDriver.findElements(By.tagName("a"));
			System.out.println("Total links present in footer are "+footerLinkCount);
			
//			for(WebElement link:footerLinks)
//			{
//				System.out.println(link.getAttribute("href"));
//			}
			
			int maxTab=10;
//			
			for(int i=0;i<Math.min(footerLinkCount,maxTab);i++)
			{
				String href = footerDriver.findElements(By.tagName("a")).get(i).getAttribute("href");
				if(href==null || href.isEmpty() || !footerDriver.findElements(By.tagName("a")).get(i).isDisplayed())
				{
					continue;
				}
//				String openLinkInSeparateTab=Keys.chord(Keys.CONTROL,Keys.ENTER);
//				footerDriver.findElements(By.tagName("a")).get(i).sendKeys(openLinkInSeparateTab);
				((JavascriptExecutor)driver).executeScript("window.open(arguments[0]);", href);
				Thread.sleep(1000); 
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
