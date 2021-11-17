package pages;

import framework.Browser;
import org.openqa.selenium.WebDriver;

public abstract class BasePage{

    WebDriver driver = Browser.driver;
    public abstract void isRightPageOpenedAssertion();
}
