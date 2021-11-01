import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends ConfigureClass {

    public static String topNavElementXpath = "//ul[@class='b-main-navigation']//span[contains(text(), '%s')]";
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void topNavigationChoice(String topNavChoice){
        driver.findElement(By.xpath(String.format(topNavElementXpath, topNavChoice))).click();
    }
}
