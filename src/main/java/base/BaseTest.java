package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.Config;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver ;
    protected WebDriverWait wait;

    @BeforeMethod
    public  void Setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.baseURL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Config.WaitTime));
    }

    @AfterMethod
    public  void TearDown(){
        if(driver!= null ){
            System.out.println("Closing browser...");
            driver.quit();
        }
    }
}
