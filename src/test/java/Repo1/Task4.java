package Repo1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import Utils.ExcelUtils;

import java.time.Duration;

public class Task4 {

    WebDriver driver;

    @BeforeMethod
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        return ExcelUtils.getTestData("LoginData.xlsx", "LoginData");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        driver.get("https://demo.guru99.com/V4/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));

        driver.findElement(By.name("uid")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("btnLogin")).click();

        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            alertWait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            System.out.println("❌ Login Failed for user: " + username + " | Alert: " + alert.getText());
            alert.accept();
            Assert.fail("Login failed due to alert presence.");
        } catch (TimeoutException e) {
            try {
                WebElement managerInfo = driver.findElement(By.xpath("//td[contains(@style,'green')]"));
                String text = managerInfo.getText();
                System.out.println("✅ Login Passed for user: " + username + " | " + text);
                Assert.assertTrue(text.contains("Manger Id"), "Login successful but text validation failed.");
            } catch (NoSuchElementException ex) {
                Assert.fail("Login possibly failed: Success message not found.");
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
