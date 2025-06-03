package Repo2;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File11 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String namestoAdd[] = { "Tomato", "Mushroom", "Onion" };
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		Wait<WebDriver> w = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(15))
			    .pollingEvery(Duration.ofMillis(500))
			    .ignoring(NoSuchElementException.class);

			

		try {

			List<String> itemToAdd = Arrays.asList(namestoAdd);
			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
			addItems(driver, itemToAdd);
			driver.findElement(By.cssSelector("img[alt=\"Cart\"]")).click();
			driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoCode")));

			// Thread.sleep(1000);
			driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
			driver.findElement(By.className("promoBtn")).click();
			// Thread.sleep(1000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("promoInfo")));
			WebElement element = w.until(d -> {
			    try {
			        WebElement promo = d.findElement(By.className("promoInfo"));
			        return promo.isDisplayed() ? promo : null;
			    } catch (Exception e) {
			        return null; // continue waiting
			    }
			});
			Assert.assertEquals(driver.findElement(By.className("promoInfo")).getText(), "Code applied ..!");

			driver.findElement(By.xpath("//button[text()='Place Order']")).click();
			Select select = new Select(
					driver.findElement(By.xpath("//label[text()='Choose Country']/following-sibling::*[2]/*[1]")));
			select.selectByValue("India");

			driver.findElement(By.className("chkAgree")).click();
			driver.findElement(By.xpath("//button[text()='Proceed']")).click();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

	public static void addItems(WebDriver driver, List<String> itemToAdd) {
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		int count = 0;
		for (int i = 0; i < products.size(); i++) {
			String name = products.get(i).getText();
			String[] splittedName = name.split("-");
			String formattedName = splittedName[0].trim();

			if (itemToAdd.contains(formattedName)) {
				driver.findElements(By.cssSelector(".product-action button")).get(i).click();
				count++;
				if (count == itemToAdd.size()) {
					System.out.println("I have added all " + count + " items to cart");
					break;
				}
			}

		}

	}

}
