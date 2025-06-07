package Repo2;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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

public class File23 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// options.addArguments("--incognito"); // <== RUNS CHROME IN INCOGNITO MODE
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			driver.get("https://www.amazon.com/");
			int totalLinkCount = driver.findElements(By.tagName("a")).size();
			List<WebElement> allLinks = driver.findElements(By.tagName("a"));
			System.out.println("Total links present in this page are " + totalLinkCount);
			WebElement footerDriver = driver.findElement(By.cssSelector(".navFooterVerticalRow.navAccessibility"));
			int footerLinkCount = footerDriver.findElements(By.tagName("a")).size();
			List<WebElement> footerLinks = footerDriver.findElements(By.tagName("a"));
			System.out.println("Total links present in footer are " + footerLinkCount);
			
//			int linkIndex=1;
//			for (WebElement link : footerLinks) {
//				
//			String url=link.getAttribute("href");
//			HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
//			conn.setRequestMethod("HEAD");
//			conn.connect();
//			System.out.println("Link "+linkIndex+": "+ conn.getResponseCode());
//			if(conn.getResponseCode() >= 400)
//			{
//				System.out.print(link.getText().trim()+"\n");
//			}
//			linkIndex++;
//				
//			}
			
			int linkIndex=1;
			for (WebElement link : allLinks) {
				
			String url=link.getAttribute("href");
			if(url==null || url.isEmpty() || !url.startsWith("http"))
			{
				linkIndex++;
				continue;
				
			}
			HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			System.out.println("Link "+linkIndex+": "+ conn.getResponseCode());
			if(conn.getResponseCode() == 404)
			{
				System.out.print(link.getText().trim()+"\n");
			}
			linkIndex++;
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.sleep(5000);
			driver.quit();
		}

	}

}
