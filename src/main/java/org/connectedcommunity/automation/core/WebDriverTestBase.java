package org.connectedcommunity.automation.core;

/**
 * Created by user on 17.05.17.
 */

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.connectedcommunity.automation.util.PropertiesCache;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;


public abstract class WebDriverTestBase {

    public static final String LOAD_TIMEOUT = "webdriver.load.timeout";
    private static final String WEB_DRIVER_GECKO = "webdriver.gecko";
    private static final String WEB_DRIVER_CHROME = "webdriver.chrome";
    private static final String CHROME_PATH = "chrome.path";
    private static final String GECKO_DRIVER_PATH_UNIX = "geckodriver.path.unix";
    private static final String GECKO_DRIVER_PATH_WIN = "geckodriver.path.win";
    private static final String IMPLICIT_WAIT = "webdriver.implicit.wait";
    private static final String SCRIPT_TIMEOUT = "webdriver.script.timeout";
    private static final String OS = "win";
    private static final String BROWSER = System.getProperty("browser");
    protected WebDriver driver;
    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


    @BeforeSuite
    public void setUp() {
        if (isBrowserSetUpFor(BrowserName.CHROME.name(), BROWSER)) {
            System.setProperty(getProperty(WEB_DRIVER_CHROME), getPath(getProperty(CHROME_PATH)));
        } else if (isBrowserSetUpFor(BrowserName.FIREFOX.name(), BROWSER)) {
            if (isWindows()) {
                System.setProperty(getProperty(WEB_DRIVER_GECKO), getPath(getProperty(GECKO_DRIVER_PATH_WIN)));
            } else if (isUnix()) {
                System.setProperty(getProperty(WEB_DRIVER_GECKO), getPath(getProperty(GECKO_DRIVER_PATH_UNIX)));
            }
        }
        initializeWebDriver();
    }


    private void initializeWebDriver() {
        if (isBrowserSetUpFor(BrowserName.CHROME.name(), BROWSER)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            driver = new ChromeDriver();
            desiredCapabilities.setBrowserName(BrowserName.CHROME.name());
        } else if (isBrowserSetUpFor(BrowserName.FIREFOX.name(), BROWSER)) {
            driver = new FirefoxDriver();
            desiredCapabilities.setBrowserName(BrowserName.FIREFOX.name());
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().setScriptTimeout(Long.valueOf(getProperty(SCRIPT_TIMEOUT)), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.valueOf(getProperty(LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(getProperty(IMPLICIT_WAIT)), TimeUnit.SECONDS);
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private boolean isBrowserSetUpFor(String browserName, String browserSystemVeriable) {
        return StringUtils.isEmpty(BROWSER) || browserName.equalsIgnoreCase(browserSystemVeriable);

    }

    private boolean isWindows() {
        return OS.contains("win");
    }

    private boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    private String getProperty(String key) {
        return PropertiesCache.getInstance().getProperty(key);
    }


    private String getPath(String s) {
        String path = null;
        URL url = WebDriverTestBase.class.getClassLoader().getResource(s);
        if (url != null) {
            path = url.getPath();
        } else {
            System.out.println("Not found resource");
        }
        return path;
    }
}
