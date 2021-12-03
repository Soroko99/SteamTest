package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.function.Function;

public abstract class BasePage{

    PropertyManager propertyManager = new PropertyManager();
    public WebDriver driver = Browser.driver;
    public abstract void isRightPageOpenedAssertion(String currentValue);

    public void waitUntilExpectedConditions(Function expectedConditions){
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
        wait.until(expectedConditions);
    }

    public String getTitle(){
        return driver.getTitle();
    }
}
