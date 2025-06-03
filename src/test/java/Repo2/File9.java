package Repo2;

import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		try {

		driver.get("http://spicejet.com/");

		driver.findElement(By.cssSelector("div[data-testid='to-testID-origin']")).click();
		driver.findElement(By.xpath("//div[contains(text(), 'BBI')]")).click();
		driver.findElement(By.xpath("//div[contains(text(), 'BOM')]")).click();
		checkReturnDateDisabled(driver);

		// driver.findElement(By.xpath("//div[contains(text(), 'Departure
		// Date')]")).click();

		int day = LocalDate.now().getDayOfMonth();
		String dateXpath = String.format("//div[text()='%d']", day + 17);
		driver.findElement(By.xpath(dateXpath)).click();

		driver.findElement(By.xpath("//div[text()='Passengers']")).click();
		driver.findElement(By.xpath("//div[@data-testid='Adult-testID-plus-one-cta']")).click();

		driver.findElement(By.xpath("//div[text()='Currency']")).click();
		driver.findElement(By.xpath("//div[text()='USD']")).click();

		driver.findElement(By.xpath("//div[text()='Family & Friends']")).click();

		driver.findElement(By.xpath("//div[@data-testid='home-page-flight-cta']")).click();

		Thread.sleep(3000);

		WebElement labelText = driver.findElement(By.xpath("//*[contains(text(),'I have read and accept the')]"));

		// Click the checkbox before the label
		WebElement checkbox = labelText.findElement(By.xpath("./preceding::div[@data-focusable='true'][1]"));
		checkbox.click();
		driver.findElement(By.xpath("//div[text()='Continue']/ancestor::div[@data-focusable='true']")).click();

		Thread.sleep(5000);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			driver.quit();
		}

		

	}

	public static void checkReturnDateDisabled(WebDriver driver) {
		if (driver.findElement(By.cssSelector("div[data-testid*=\"return-date\"]")).getDomAttribute("style")
				.contains("rgb")) {
			System.out.println("Deparature date is disabled");
			Assert.assertTrue(true);
		} else {
			System.out.println("Deparature date is enabled");
			Assert.assertTrue(false);
		}
	}

}
