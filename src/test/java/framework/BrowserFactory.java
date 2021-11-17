package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import java.util.HashMap;

public class BrowserFactory{

    public  static WebDriver createDriver(){
        PropertyManager propertyManager = new PropertyManager();
        String browser = propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "browser");
        WebDriver driver = null;
        switch (browser) {
            case "chrome" -> {
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("safebrowsing.enabled", "true");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
            }
            case "firefox" -> {
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "steam_save_dir"));
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions.setProfile(profile));
            }
            default -> Assert.fail(browser + " " + "нет такого драйвера!(");
        }
        return driver;
    }
}
