package Repo1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * 
 * @author Sameer Ranjan Panda
 *        Test Script 01
 *        ************** 
 *        Test Steps
 *        1)  Go to http://www.demo.guru99.com/V4/
          2) Enter valid UserId
          3) Enter valid Password
          4) Click Login
 */

public class Task1 {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();

        // ✅ Disable DevTools integration to avoid 403 error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Recommended for some newer Chrome builds
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("w3c", true);  // Use W3C WebDriver protocol (avoids CDP)

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().window().maximize();
            driver.get("https://www.demo.guru99.com/V4/");

            driver.findElement(By.name("uid")).sendKeys("mngr623555");
            driver.findElement(By.name("password")).sendKeys("AnEjEde");
            driver.findElement(By.name("btnLogin")).click();

           

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
