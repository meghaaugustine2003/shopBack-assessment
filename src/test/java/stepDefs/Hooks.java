package stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.shopback.sg/");
        driver.manage().window().maximize();
    }

    @After
    public void terminateTest() {
        driver.quit();
    }

    @Before(value = "@Login")
    public void userLogin() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='header__auth-btn']")).click();
        driver.findElement(By.xpath("//span[text()='Login']")).click();
        driver.findElement(By.xpath("//span[text()='Use Email Address']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("qaassignment@shopback.com");
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Shopback22");
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        Thread.sleep(3000);
    }
}
