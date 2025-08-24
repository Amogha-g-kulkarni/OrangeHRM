package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;


public class LoginPage {
    public WebDriver webDriver ;
    public WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait){
         this.webDriver = driver;
        this.wait = wait;
    }

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.className("orangehrm-login-button");
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    String dashboardURL ="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";



public boolean testLogin(String username, String password){
    webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();

 try{
    wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
    return  true;
 }
 catch (TimeoutException e){
     return false;
 }
}
}

