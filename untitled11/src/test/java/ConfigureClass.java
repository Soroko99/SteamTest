import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ConfigureClass {
    public static WebDriver driver;
    protected static String mainUrl = "https://www.onliner.by/";

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(mainUrl);
    }

    @AfterTest
    public void driverClose(){
        driver.quit();
    }

    public void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}