package framework.elements;

import framework.Browser;
import framework.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement{

    WebDriver driver = Browser.driver;
    By locator;
    WebElement element;
    PropertyManager propertyManager = new PropertyManager();

    public BaseElement(By locator){
        this.locator = locator;
    }

    public void click() {
        waitForIsElementPresent(locator);
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    public void waitForIsElementPresent(By locator){
        element = driver.findElement(locator);
        isPresent(locator);
        if(!element.isDisplayed()){
            System.out.println("Bad news");
        }
        Assert.assertTrue(element.isDisplayed());
    }

    private boolean isPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        List<WebElement> list = driver.findElements(locator);
                        for (WebElement el : list) {
                            if (el != null && el.isDisplayed()) {
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
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "implicit_wait")), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
