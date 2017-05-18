package org.connectedcommunity.automation.util;

import org.connectedcommunity.automation.core.WebDriverTestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 30.03.17.
 */
public class WebDriverUtils {
    private WebDriverWait wait;

    public WebDriverUtils(WebDriver driver) {
        wait = new WebDriverWait(driver,
                Long.valueOf(PropertiesCache.getInstance().getProperty(WebDriverTestBase.LOAD_TIMEOUT)));
    }

    public void waitForExpectedCondition(ExpectedCondition expectedCondition) {
        wait.until(expectedCondition);
    }

}
