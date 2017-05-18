package org.connectedcommunity.automation;

import org.connectedcommunity.automation.core.WebDriverTestBase;
import org.connectedcommunity.automation.pages.HomePage;
import org.connectedcommunity.automation.pages.LoginPage;
import org.connectedcommunity.automation.pages.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

@Title("User logout test")
@Description("Testing user logout")
public class UserLogoutTest extends WebDriverTestBase {
    private String applicationURL = "https://automation.connectedcommunity.org/home";
    private String userLogin = "qa@higherlogic.test";
    private String userPassword = "qa";
    private HomePage homePage;

    @BeforeMethod
    public void userLogin() {
        homePage = new HomePage(driver);
        homePage.openURL(applicationURL);
        homePage.openLoginWindow();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailAddressField(userLogin);
        loginPage.fillPasswordFiled(userPassword);
        loginPage.clickOnLoginButton();
    }

    @Test
    public void testUserLogout() {
        homePage.clickOnShowProfileButton();
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.clickOnLogoutButton();
        Assert.assertTrue(homePage.getSignInButton().isDisplayed());
    }

}
