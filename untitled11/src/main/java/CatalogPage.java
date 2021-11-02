import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CatalogPage {
    public String catalogSectionXpath = "//ul//span[contains(text(),'%s')]//ancestor::li";
    public String electronicsSectionXpath = "//div[contains(text(), '%s')]";
    public String productXpath = "//span[contains(text(),'%s')]//ancestor::a";
    public String tvExpectedTitle = "Телевизор купить в Минске";
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
    public void tvTitleAssertion(){
        Assert.assertEquals(driver.getTitle(), tvExpectedTitle);
    }
}
