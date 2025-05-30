package Repo2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File7 {

	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

		driver.findElement(By.id("autosuggest")).sendKeys("ch");
		Thread.sleep(1000);
		List<WebElement> option = driver.findElements(By.xpath("//a[contains(@id,'ui-id')]"));
		for (WebElement a : option) {
			// System.out.println(a.getText());
			if (a.getText().equals("China")) {
				a.click();
				Thread.sleep(1000);
			}
		}

		// ---------Static Dropdown
		WebElement dropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select select = new Select(dropdown);
		select.selectByIndex(2);
		
		//-------------Dynamic Dropdown
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXTaction")).click();
		driver.findElement(By.cssSelector("a[value=\"IXB\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@value='CCU'])[2]")).click();

		driver.quit();
	}
}
