package framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class Browser extends BrowserFactory{

    @BeforeTest
    public void driverInit() throws IOException {
        createDriver();
    }

    @AfterTest
    public void driverClose(){
        driver.quit();
    }
}
