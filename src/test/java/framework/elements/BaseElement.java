package framework.elements;

import framework.Browser;
import framework.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement{

    WebDriver driver = Browser.driver;
    WebDriverWait wait = new WebDriverWait(driver, 5);
    By locator;
    WebElement element;
    List<WebElement> elementList;

    public BaseElement(By locator){
        this.locator = locator;
    }

    public boolean isDisplayed(){
        return driver.findElement(locator).isDisplayed();
    }

    public void click() {
        waitUntilPresent();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    public void clickViaJS() {
        waitUntilPresent();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", element);
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public List<WebElement> getElementList(){
        if (arePresent()) return elementList;
        else return null;
    }

    public WebElement getElement(){
        waitUntilPresent();
        return element;
    }

    public void moveTo(){
        Actions actions = new Actions(driver);
        waitUntilPresent();
        actions.moveToElement(driver.findElement(locator)).build().perform();
    }

    public String getText(){
        return driver.findElement(locator).getText();
    }

    private boolean arePresent() {
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        elementList = driver.findElements(locator);
                        for (WebElement el : elementList) {
                            if (el != null && el.isDisplayed()) {
                                element = el;
                                return element.isDisplayed();
                            }
                        }
                        element = driver.findElement(locator);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isPresent() {
        try {
            element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean waitUntilPresent() {
        wait.until((ExpectedCondition<Boolean>) (x) -> {
            try {
                return isPresent();
            }catch (Exception e){
                return false;
            }
            });
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
