package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.Config;

import java.util.Objects;


public class LoginPage {
    public WebDriver webDriver ;
    public WebDriverWait wait;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.className("orangehrm-login-button");
    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private By profileDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    private By logoutButton = By.xpath("//li/a[@class='oxd-userdropdown-link' and text() = 'Logout']");

    String loginPageUrl = Config.baseURL;
    String dashboardURL = Config.dashboardURL;

    public LoginPage(WebDriver driver, WebDriverWait wait){
         this.webDriver = driver;
        this.wait = wait;
    }


public boolean testLogin(String username, String password){
    webDriver.get(loginPageUrl);
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();

    try {
        wait.until(ExpectedConditions.urlToBe(dashboardURL));
        return true;
    } catch (TimeoutException e) {
        return false;
    }
}

public boolean testLogout (){
    webDriver.get(loginPageUrl);
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("Admin");
    wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys("admin123");
    wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(profileDropdown)).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).click();
    try {
        boolean urlMatched = wait.until(ExpectedConditions.urlToBe(
                loginPageUrl
        ));
        return urlMatched;
    } catch (TimeoutException e) {
        return false;
    }
}

}

