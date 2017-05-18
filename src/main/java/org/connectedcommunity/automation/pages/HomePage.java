package org.connectedcommunity.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage extends AbstractPage {

    @FindBy(id = "Welcome_LoginLink")
    private WebElement signInButton;

    @FindBy(css = ".HtmlContent>font>span")
    private WebElement qaUserViewLabel;

    @FindBy(id = "BtnShowProfile")
    private WebElement showProfileButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Get user view label")
    public WebElement getUserViewLabel() {
        return qaUserViewLabel;
    }

    @Step("Click on Show Profile Button")
    public void clickOnShowProfileButton() {
        showProfileButton.click();
    }

    @Step("Open LoginPage")
    public LoginPage openLoginWindow() {
        signInButton.click();
        return new LoginPage(driver);
    }

    @Step("Get SignIn button")
    public WebElement getSignInButton() {
        return signInButton;
    }

}
