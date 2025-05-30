package Repo2;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class File6 {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
        Thread.sleep(2000); // wait for page to load

        WebElement dropDown = driver.findElement(By.xpath("//select"));
        Thread.sleep(1000); // wait before interacting

        Select select = new Select(dropDown);
        List<WebElement> option = select.getOptions();

        ArrayList<String> optionText = new ArrayList<>();

        for (int i = 0; i < option.size(); i++) {
            optionText.add(option.get(i).getText());
             // slow down loop (optional)
        }

        Thread.sleep(1000);
        select.selectByIndex(5);
        System.out.println(select.getFirstSelectedOption().getText());
        Thread.sleep(1000);

        select.selectByValue("ALB");
        System.out.println(select.getFirstSelectedOption().getText());
        Thread.sleep(1000);

        select.selectByVisibleText("Argentina");
        System.out.println(select.getFirstSelectedOption().getText());
        Thread.sleep(1000);

        System.out.println(optionText);
        Thread.sleep(1000);

        driver.quit();
    }
}
