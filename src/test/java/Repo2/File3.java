package Repo2;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File3 {

	public static void main(String[] args) {

// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");

		WebDriver driver = new ChromeDriver(option);

// Sibling - Child to parent traverse

//header/div/button[1]/following-sibling::button[1]

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		System.out
				.println(driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]")).getText());

		System.out.println(driver.findElement(By.xpath("//header/div/button[1]/parent::div/button[2]")).getText());

		driver.quit();

	}

}
