package Repo1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * @author Sameer Ranjan Panda
 * Modifcation of Test Script 01
 * ***************************** 
 * */
public class Task2 {

	static WebDriver driver;

	public static void main(String[] args) {
		setupDriver();
		driver.get(Util.BASE_URL);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Util.WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));

		driver.findElement(By.name("uid")).sendKeys(Util.USERNAME);
		driver.findElement(By.name("password")).sendKeys(Util.PASSWORD);
		driver.findElement(By.name("btnLogin")).click();

		String managerName = driver.findElement(By.xpath("//td[contains(@style,\"green\")]")).getText();
		System.out.println(managerName);
		if (managerName.contains(Util.USERNAME)) {
			System.out.println("QC Pass");
		} else {
			System.out.println("QC Failed");
		}

		tearDown();

	}

	public static void setupDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*"); // Recommended for some newer Chrome builds
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}

}
