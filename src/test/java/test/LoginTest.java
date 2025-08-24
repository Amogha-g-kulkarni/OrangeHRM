package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object [][]{
                {"Admin", "admin123", true},
                {"Adm", "admin123", false},
                {"AMOgha","",false},
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean expectedResult) {
        LoginPage loginPage = new LoginPage(driver, wait);
        boolean actualResult = loginPage.testLogin(username, password);

        Assert.assertEquals(actualResult, expectedResult,
                "Login result mismatch for: " + username + " / " + password);
    }

    @Test
    public void testLogout(){
        LoginPage loginPage = new LoginPage(driver, wait);
        boolean result = loginPage.testLogout();
        Assert.assertTrue(result,"Logout failed");
    }

}
