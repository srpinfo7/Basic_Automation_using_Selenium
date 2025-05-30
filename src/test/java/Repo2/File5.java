package Repo2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File5 {

	static WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless","--remote-allow-origins=*");
	        driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
	        
	        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	        
	        WebElement firstCheckbox=driver.findElement(By.id("checkBoxOption1"));
	        firstCheckbox.click();
	        Assert.assertTrue(firstCheckbox.isSelected());
	        firstCheckbox.click();
	        Assert.assertFalse(firstCheckbox.isSelected());
	        
	        int checkBoxCount=driver.findElements(By.cssSelector("input[type='checkbox']")).size();
	        System.out.println(checkBoxCount);
	        
	        
	}

}
