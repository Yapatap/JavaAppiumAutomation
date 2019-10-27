import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName ","Appium");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/user/IdeaProjects/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("","");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void firstTest() {
        waitForElementByXpathAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find element",
                5
        );
        waitForElementByXpathAndSendKeys(
                "//*[contains(@text,'Search…')]",
                "Java",
                "Cannot find element 'Java'",
                5
        );
        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Object-oriented programming language']",
                "Cannot find 'Object-oriented programming language' topic",
                15
        );


    }
    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
}
