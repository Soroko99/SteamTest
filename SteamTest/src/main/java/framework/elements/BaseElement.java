package framework.elements;

import framework.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

import java.util.List;

public class BaseElement extends BasePage {
    PropertyManager propertyManager = new PropertyManager();
    protected JavascriptExecutor js;
    protected Actions actions = new Actions(driver);



    public BaseElement(WebDriver driver) {
        super(driver);
    }

    public BaseElement(WebDriver driver, JavascriptExecutor js)
    {
        super(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public BaseElement(WebDriver driver, JavascriptExecutor js, Actions actions){
        super(driver);
        this.js = (JavascriptExecutor) driver;
        this.actions = (Actions) driver;
    }

    public WebElement waitElementIsVisible(WebElement element){
        return new WebDriverWait(driver, Long.parseLong(propertyManager.getExactProperty(seleniumProperty, "explicit_wait"))).
                until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> findElementsList(String xPath){
        List<WebElement> list = driver.findElements(By.xpath(xPath));
        waitElementIsVisible(list.get(list.size() - 1));
        return list;
    }

    public void click(WebElement element)
    {
        waitElementIsVisible(element);
        element.click();
    }

    public void clickViaJS(WebElement element)
    {
        js.executeScript("arguments[0].click();", waitElementIsVisible(element));
    }

    public WebElement getElement(WebElement element){
        waitElementIsVisible(element);
        return element;
    }

    public List<WebElement> getElementsList(List<WebElement> list){
        waitElementIsVisible(list.get(list.size()-1));
        return list;
    }

    public WebElement searchElement(String xPath){
        return waitElementIsVisible(driver.findElement(By.xpath(xPath)));
    }

    public void hover(WebElement element){
        waitElementIsVisible(element);
        actions.moveToElement(element).build().perform();
    }

}
