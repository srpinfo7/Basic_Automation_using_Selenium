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

public class File13 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--incognito"); // <== RUNS CHROME IN INCOGNITO MODE
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {

			driver.get("https://rahulshettyacademy.com/loginpagePractise/");
			driver.findElement(By.cssSelector("a[class=\"blinkingText\"]")).click();
			
			Set<String> windows=driver.getWindowHandles();
			Iterator<String> it=windows.iterator();
			String parent=it.next();
			String child=it.next();
			driver.switchTo().window(child);			
			String credentialText=driver.findElement(By.cssSelector(".im-para.red")).getText();
			System.out.println(credentialText);
			String email=getEmail(credentialText);
			System.out.println(email);
			driver.switchTo().window(parent);
			driver.findElement(By.id("username")).sendKeys(email);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.sleep(2000);
			driver.quit();
		}

	}


	public static String getEmail(String text) {
		String emailText = text.split("at")[1];
		String email = emailText.split("with")[0].trim();
		return email;
	}

}
