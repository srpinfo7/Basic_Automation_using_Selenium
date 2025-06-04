package Repo2;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File12 {

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
			String credenText = driver.findElement(By.cssSelector(".text-center.text-white")).getText();
			getCredentials(credenText);
			driver.findElement(By.id("username")).sendKeys(getCredentials(credenText).get("userId"));
			driver.findElement(By.id("password")).sendKeys(getCredentials(credenText).get("password"));
			driver.findElement(By.xpath("(//*[@class='checkmark'])[2]")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
			driver.findElement(By.id("okayBtn")).click();
			WebElement drpdwn = driver.findElement(By.xpath("//select[@class='form-control']"));
			Select dropdown = new Select(drpdwn);
			dropdown.selectByValue("consult");
			driver.findElement(By.name("terms")).click();
			driver.findElement(By.id("signInBtn")).click();

			wait.until(ExpectedConditions.urlContains("https://rahulshettyacademy.com/angularpractice/shop"));

			List itemsToBeAdded = driver.findElements(By.cssSelector(".btn.btn-info"));
			addItems(driver, itemsToBeAdded);

			driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-success")));
			driver.findElement(By.cssSelector(".btn.btn-success")).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
			driver.findElement(By.id("country")).sendKeys("India");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='India']")));
			driver.findElement(By.xpath("//a[text()='India']")).click();
			driver.findElement(By.xpath("//*[contains(text(),'I agree with the')]")).click();
			driver.findElement(By.cssSelector("input[value='Purchase']")).click();

			wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(text(),'Success!')]")));
			String orderConfirmMessage = driver.findElement(By.xpath("//*[contains(text(),'Success!')]")).getText();
			// System.out.println(orderConfirmMessage);
			Assert.assertEquals("Success!", orderConfirmMessage);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

	public static void addItems(WebDriver driver, List<WebElement> itemToAdd) {
		for (int i = 0; i < itemToAdd.size(); i++) {
			itemToAdd.get(i).click();
		}

	}

	public static HashMap<String, String> getCredentials(String text) {
		String userText = text.split("and")[0];
		String userId = userText.split("is")[1].trim();
		String passText = text.split("and")[1];
		String password = passText.split("is")[1].trim();
		password = password.replace(")", "");
		HashMap<String, String> credentials = new HashMap<>();
		credentials.put("userId", userId);
		credentials.put("password", password);

		// Return the populated map
		return credentials;
	}

}
