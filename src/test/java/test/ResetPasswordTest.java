package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ResetPasswordPage;

public class ResetPasswordTest extends BaseTest {

    ResetPasswordPage resetPasswordPage;
    @BeforeMethod
    public void setup(){
        resetPasswordPage = new ResetPasswordPage(driver,wait);
    }
    @Test
    public void testNavigationToResetPassword(){
        boolean result = resetPasswordPage.navigateToPasswordReset();
        Assert.assertTrue(result, "Navigation to reset password page should be successful");
    }
    @Test
    public  void testResetPassword(){
        String result = resetPasswordPage.resetPassword("Admin");
        Assert.assertEquals(result, "Reset email is sent", "Reset password should succeed with valid username");
    }

    @Test
    public  void testResetPasswordWithEmptyUsername(){
        String result = resetPasswordPage.resetPassword("");
        Assert.assertEquals(result, "Required username", "Reset password should fail when username is empty");
    }
    @Test
    public void testCancelButtonRedirection(){
        boolean result = resetPasswordPage.checkCancelRedirection();
        Assert.assertTrue(result);
    }
}
