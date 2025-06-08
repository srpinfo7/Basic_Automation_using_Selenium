package Repo2;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class File27 {

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

			driver.get("https://rahulshettyacademy.com/angularpractice/");
			WebElement nameBox=driver.findElement(By.cssSelector(".form-control.ng-untouched.ng-pristine.ng-invalid"));
			String nameBoxLabel=driver.findElement(with(By.tagName("label")).above(nameBox)).getText();
			WebElement emailBox=driver.findElement(with(By.tagName("input")).below(nameBox));
			WebElement passBox=driver.findElement(with(By.tagName("input")).below(emailBox));
			WebElement gender=driver.findElement(By.id("exampleFormControlSelect1"));
			WebElement ackCheckbox = driver.findElement(By.id("exampleCheck1"));
			WebElement employed=driver.findElement(By.id("inlineRadio2"));
			WebElement Student=driver.findElement(with(By.tagName("input")).toLeftOf(employed));
			WebElement employed1=driver.findElement(with(By.tagName("input")).toRightOf(Student));
			WebElement date=driver.findElement(By.cssSelector("input[type=\"date\"]"));
			WebElement submitButton=driver.findElement(with(By.tagName("input")).below(date));
			
		//	driver.switchTo().newWindow(WindowType.TAB);
			driver.switchTo().newWindow(WindowType.WINDOW);
			Set<String> windows = driver.getWindowHandles();
	        Iterator<String> it = windows.iterator();
	        String parentTab = it.next();
	        String newTab = it.next();
	        driver.switchTo().window(newTab);

	        driver.get("https://rahulshettyacademy.com/");
	        String firstCourse = driver.findElements(By.xpath("//h2/a")).get(0).getText();
	        System.out.println("First course name: " + firstCourse);
	        
	        driver.switchTo().window(parentTab);
			
			System.out.println(nameBoxLabel);
			nameBox.sendKeys(firstCourse);
			
			//Screenshot
			File f=nameBox.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f, new File("logo.png"));
			
			//Get Dimensions
			
			System.out.println(nameBox.getRect().getDimension().getHeight());
			System.out.println(nameBox.getRect().getDimension().getWidth());
			System.out.println(nameBox.getRect().getDimension().getClass());
			
			
			
			emailBox.sendKeys("sm@email.com");
			passBox.sendKeys("1234");
			ackCheckbox.click();
			
			Select select=new Select(gender);
			System.out.println("Selecting gender "+select.getOptions().get(1).getText());
			select.selectByVisibleText("Female");
			
			Thread.sleep(1000);
			employed.click();
			Thread.sleep(1000);
			Student.click();
			
			date.sendKeys("11112025");
			submitButton.click();
			System.out.println("Clicked on Submit");


			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Thread.sleep(5000);
			driver.quit();
		}

	}


}
