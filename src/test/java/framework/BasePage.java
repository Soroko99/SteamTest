package framework;

import org.openqa.selenium.WebDriver;

public abstract class BasePage{

    public WebDriver driver = Browser.driver;
    public abstract void isRightPageOpenedAssertion(String currentValue);
}
