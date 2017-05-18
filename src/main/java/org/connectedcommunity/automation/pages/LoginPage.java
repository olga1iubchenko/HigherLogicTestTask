package org.connectedcommunity.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginPage extends AbstractPage {

    @FindBy(id = "MainCopy_Login1_UserLogin")
    private WebElement emailAddressField;

    @FindBy(id = "MainCopy_Login1_UserPass")
    private WebElement passwordField;

    @FindBy(id = "MainCopy_Login1_LoginButton")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Fill Email Address with valid email")
    public void fillEmailAddressField(String email) {
        webDriverUtils.waitForExpectedCondition(
                ExpectedConditions.visibilityOf(emailAddressField));
        emailAddressField.sendKeys(email);
    }

    @Step("Fill Password with valid input")
    public void fillPasswordFiled(String password) {
        passwordField.sendKeys(password);
    }

    @Step("Click on Login button")
    public void clickOnLoginButton() {
        loginButton.click();
    }

}
