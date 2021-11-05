package pages.base;

import framework.BrowserFactory;
import framework.PropertyManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.BrowserFactory.seleniumProperty;


public class BasePage extends BrowserFactory {
    protected WebDriver driver;


    public BasePage(){
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    }


