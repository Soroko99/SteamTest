import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends ConfigureClass {
    public static final String catalogSectionXpath = "//ul//span[contains(text(),'%s')]//ancestor::li";
    public static final String electronicsSectionXpath = "//div[contains(text(), '%s')]";
    public static final String productXpath = "//span[contains(text(),'%s')]//ancestor::a";

    WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    public void catalogSubsectionChoice(String subSectionName){
        driver.findElement(By.xpath(String.format(catalogSectionXpath, subSectionName))).click();
    }

    public void electronicsSubsectionChoice(String subSectionName){
        driver.findElement(By.xpath(String.format(electronicsSectionXpath, subSectionName))).click();
    }

    public void productChoice(String productName){
        driver.findElement(By.xpath(String.format(productXpath, productName))).click();
    }
}
