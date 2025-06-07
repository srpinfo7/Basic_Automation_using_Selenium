package Repo2;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File25 {

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

			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
			driver.findElement(By.cssSelector(".sort-icon.sort-descending")).click();
			List<WebElement> vegisList=driver.findElements(By.xpath("//tr/td[1]"));
			List<String> vegies=vegisList.stream().map(s -> s.getText()).collect(Collectors.toList());
			List<String> sortedVegies=vegies.stream().sorted().collect(Collectors.toList());
			SoftAssert asst=new SoftAssert();
			asst.assertTrue(vegies.equals(sortedVegies));
			List<String> price;
			
			do
			{
				List<WebElement> rows=driver.findElements(By.xpath("//tr/td[1]"));
				price=rows.stream().filter(s -> s.getText().contains("Wheat"))
						.map(s -> getPrice(s)).collect(Collectors.toList());
				
				if(price.size()<1)
				{
					driver.findElement(By.xpath("//a[@aria-label='Next']")).click();				
				}
				
				
			}while(price.size()<1);
			
			
			price.stream().forEach(s -> System.out.println("Price of Wheat is "+s));
			asst.assertAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.sleep(5000);
			driver.quit();
		}

	}

	private static String getPrice(WebElement s) {
		// TODO Auto-generated method stub
		String price=s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return price;
	}

}
