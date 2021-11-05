package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserFactory{

    protected static String browser;
    public static String seleniumProperty = "src/test/resources/selenium_properties.properties";
    public static WebDriver driver = null;
    public static WebDriver createDriver() throws IOException {
        PropertyManager propertyManager = new PropertyManager();
        browser = propertyManager.getExactProperty(seleniumProperty, "browser");
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                Assert.fail(browser + " " + "нет такого драйвера!(");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(propertyManager.getExactProperty(seleniumProperty, "implicit_wait")), TimeUnit.SECONDS);
        return driver;
    }
}
