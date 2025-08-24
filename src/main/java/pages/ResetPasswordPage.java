package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.Config;

public class ResetPasswordPage {
    WebDriver driver ;
    WebDriverWait wait ;

    private static final String loginPageUrl = Config.baseURL;
    private static final String resetPageUrl = Config.resetPasswordPageUrl;

    private By resetPasswordLink = By.xpath("//p[text()='Forgot your password? ']");
    private By usernameField = By.name("username");
    private By resetPasswordButton = By.xpath("//button[text()=' Reset Password ']");
    private By UsernameRequired = By.xpath("//span[text()='Required']");
    private By CancelButton = By.xpath("//button[text()=' Cancel ']");
    private By ResetSuccessMessage = By.xpath("//h6[text()='Reset Password link sent successfully']");

    public  ResetPasswordPage(WebDriver driver , WebDriverWait Wait){
        this.wait = Wait;
        this.driver = driver;
    }



    public boolean navigateToPasswordReset (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordLink)).click();
        try{
        boolean urlChanged = wait.until(ExpectedConditions.urlMatches(resetPageUrl));
        return urlChanged;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public String resetPassword(String userName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(userName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordButton)).click();

        try {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(ResetSuccessMessage)).isDisplayed()) {
                return "Reset email is sent";
            }
        } catch (TimeoutException e) {
            // Success message not found, check for error
        }

        try {
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(UsernameRequired)).isDisplayed()) {
                return "Required username";
            }
        } catch (TimeoutException e) {
            // Neither success nor error found
        }

        return "error";
    }



    public boolean checkCancelRedirection() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resetPasswordLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(CancelButton)).click();

        try {
            return wait.until(ExpectedConditions.urlMatches(
                    loginPageUrl
            ));
        } catch (TimeoutException e) {
            return false;
        }
    }


}
