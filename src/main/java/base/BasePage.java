package base;

import drivers.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import constants.Global_Constants;

import java.time.Duration;

public class BasePage {
    protected static Logger log = LogManager.getLogger();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public void navigateTo_URL(String url) {
        getDriver().get(url);
    }

    public void sendKeys(WebElement element, String textToType) {
        try {
            log.debug("Entering " + textToType + " in text field");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Constants.DEFAULT_EXPLICIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void waitFor(By by) {
        try {
            log.debug("Waiting for element to be visible");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Constants.DEFAULT_EXPLICIT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void waitFor(WebElement element) {
        try {
            log.debug("Waiting for element to be visible");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Constants.DEFAULT_EXPLICIT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Works only with xpath for now (can be extended for all locator strategies
    public void click(String locator) {
        WebElement ele = getDriver().findElement(By.xpath(locator));
        moveToElement(ele);
        waitForWebElementAndClick(ele);
    }

    public void waitForWebElementAndClick(WebElement element) {
        try {
            log.debug("Waiting for element to appear and then click");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Constants.DEFAULT_EXPLICIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            jsClick(element);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void waitForAlert_And_ValidateText(String text) {
        try {
            log.debug("Waiting for alert to validate text");
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Global_Constants.DEFAULT_EXPLICIT_TIMEOUT));
            wait.until(ExpectedConditions.alertIsPresent());
            String alert_Message_Text = getDriver().switchTo().alert().getText();
            Assert.assertEquals(alert_Message_Text, text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void moveToElement(WebElement element) {
        try {
            log.debug("Moving over element");
            waitFor(element);
            Actions action = new Actions(getDriver());
            action.moveToElement(element).build().perform();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void performEscape() {
        try {
            log.debug("Simulate Escape key operation");
            Actions action = new Actions(getDriver());
            action.sendKeys(Keys.ESCAPE).build().perform();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void wait(int sleepTimeInMs) throws InterruptedException {
        Thread.sleep(sleepTimeInMs);
    }

    public boolean isElementPresent(WebElement element) {
        {
            try {
                element.getTagName();
            } catch (NoSuchElementException e) {
                return false;
            }
            return true;
        }
    }

    public String gelElementText(String locator) {
        String value = "";
        for (int i = 0; i < 5; i++) {
            try {
                value = getDriver().findElement(By.xpath(locator)).getText();
                break;
            } catch (StaleElementReferenceException e) {
                value = getDriver().findElement(By.xpath(locator)).getText();
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }

    public String gelElementAttributeValue(String locator, String attributeName) {
        String value = "";
        for (int i = 0; i < 5; i++) {
            try {
                value = getDriver().findElement(By.xpath(locator)).getAttribute(attributeName);
                break;
            } catch (StaleElementReferenceException e) {
                value = getDriver().findElement(By.xpath(locator)).getAttribute(attributeName);
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }
}
