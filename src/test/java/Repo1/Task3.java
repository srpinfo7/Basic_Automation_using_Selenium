package Repo1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import Utils.ExcelUtils;

import java.time.Duration;

public class Task3 {

	static WebDriver driver;

	public static void main(String[] args) {
		Object[][] credentials = ExcelUtils.getTestData("LoginData.xlsx", "LoginData");

		for (Object[] cred : credentials) {
			String username = cred[0].toString();
			String password = cred[1].toString();
			runTest(username, password);
		}
	}

	public static void runTest(String username, String password) {
		setupDriver();
		driver.get("https://demo.guru99.com/V4/");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));

		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		// Wait briefly to check for alert
		try {
			WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
			alertWait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.switchTo().alert();
			System.out.println("❌ Login Failed for user: " + username + " | Alert: " + alert.getText());
			alert.accept(); // Close the alert
		} catch (TimeoutException e) {
			// No alert - check for valid login
			try {
				WebElement managerInfo = driver.findElement(By.xpath("//td[contains(@style,'green')]"));
				String text = managerInfo.getText();
				System.out.println("✅ Login Passed for user: " + username + " | " + text);
			} catch (NoSuchElementException ex) {
				System.out.println("❌ Login Possibly Failed: " + username + " | Element not found.");
			}
		}

		tearDown();
	}

	public static void setupDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
