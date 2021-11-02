import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MainPage {

    public static String topNavElementXpath = "//ul[@class='b-main-navigation']//span[contains(text(), '%s')]";
    WebDriver driver;
    public String catalogExpectedTitle = "Каталог Onlíner";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void topNavigationChoice(String topNavChoice){
        driver.findElement(By.xpath(String.format(topNavElementXpath, topNavChoice))).click();
    }

    public void catalogTitleAssertion(){
        Assert.assertEquals(driver.getTitle(), catalogExpectedTitle);
    }
}
