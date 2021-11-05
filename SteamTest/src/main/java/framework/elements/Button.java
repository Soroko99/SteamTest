package framework.elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

import javax.swing.*;

public class Button extends BaseElement {

    public Button(WebDriver driver, JavascriptExecutor js) {
        super(driver);
        this.js = js;
    }


}
