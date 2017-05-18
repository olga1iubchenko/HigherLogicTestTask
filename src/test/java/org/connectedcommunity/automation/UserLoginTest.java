package org.connectedcommunity.automation;

import org.connectedcommunity.automation.core.WebDriverTestBase;
import org.connectedcommunity.automation.pages.HomePage;
import org.connectedcommunity.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

@Title("User login test")
@Description("Testing login to the system user with valid email and password")
public class UserLoginTest extends WebDriverTestBase {

    private String applicationURL = "https://automation.connectedcommunity.org/home";
    private String userLogin = "qa@higherlogic.test";
    private String userPassword = "qa";
    private String qaUserView = "QA user View.  ";

    @Test
    public void testUserLoginWithValidInput() {
        HomePage homePage = new HomePage(driver);
        homePage.openURL(applicationURL);
        homePage.openLoginWindow();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillEmailAddressField(userLogin);
        loginPage.fillPasswordFiled(userPassword);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(homePage.getUserViewLabel().getText(), qaUserView);
    }
}
