package org.connectedcommunity.automation.pages;

import org.connectedcommunity.automation.util.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by user on 17.05.17.
 */
public class AbstractPage {
    protected WebDriver driver;
    protected WebDriverUtils webDriverUtils;

     AbstractPage(WebDriver driver) {
        this.driver = driver;
        webDriverUtils = new WebDriverUtils(this.driver);
    }

    @Step("Open URL")
    public void openURL(String url) {
        driver.get(url);
    }
}
