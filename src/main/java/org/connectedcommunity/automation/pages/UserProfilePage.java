package org.connectedcommunity.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

public class UserProfilePage extends AbstractPage {

    @FindBy(id = "Welcome_Details_logoutLink_0")
    private WebElement logoutButton;

    public UserProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("User logged out from the system")
    public HomePage clickOnLogoutButton() {
        logoutButton.click();
        return new HomePage(driver);
    }

}
