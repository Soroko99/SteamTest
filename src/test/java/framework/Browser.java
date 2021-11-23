package framework;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import static framework.BrowserFactory.createDriver;

public class Browser{

    public static WebDriver driver;
    PropertyManager propertyManager = new PropertyManager();

    public void driverClose()
    {
        driver.quit();
    }

    public void setup(){
        driver = createDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "implicit_wait")), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "implicit_wait")), TimeUnit.SECONDS);
        driver.get(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "main_url"));
    }

    public void get(String url){
        driver.get(url);
    }

}
