import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TVPage {
    WebDriver driver;

    public TVPage(WebDriver driver, JavascriptExecutor js) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public JavascriptExecutor js;
    public Select selectSize;
    protected String manufacturerFilterXpath = "//input[@value='%s']//ancestor::label";
    protected String priceFiltrationXpath = "//input[contains(@placeholder, '%s')]";
    protected String resolutionXpath = "//span[contains(text(), '%s')]/parent::label";
    protected String screenSizeXpath = "//select[contains(@data-bind, 'value: facet.value.%s')]";
    protected String commonXpath = "//div[@class='schema-product']";

    public void manufacturerFiltration(String manufacturerName){
        driver.findElement(By.xpath(String.format(manufacturerFilterXpath, manufacturerName).toLowerCase(Locale.ROOT))).click();
    }

    public void priceFiltration(String placeholderText, String keysToSend)
    {
        driver.findElement(By.xpath(String.format(priceFiltrationXpath, placeholderText))).sendKeys(keysToSend);
    }

    public void resolutionFiltration(String resolutionIs){
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath(String.format(resolutionXpath, resolutionIs))));
    }

    public void screenSizeFiltration(String fromOrTo, String screenSize){
        selectSize = new Select(driver.findElement(By.xpath(String.format(screenSizeXpath, fromOrTo))));
        selectSize.selectByValue(Integer.toString(Integer.parseInt(screenSize) * 10));
    }

    public List<String> generatingTvCharacteristicsList(WebDriver driver, String specialXpath){
        List<WebElement> list = driver.findElements(By.xpath(commonXpath));
        List<String> paramsList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            paramsList.add(list.get(i).findElements(By.xpath(String.format("%s", specialXpath))).get(i).getText());
        }
        return paramsList;
    }

    public void manufacturerValidation(List<String> list, String manufacturer) {
        for (String currentManufacturer : list) {
            Assert.assertTrue(currentManufacturer.contains(manufacturer));
        }
    }

    public void maxPriceValidation(List<String> list, String price){
        for (String currentPrice : list){
            Assert.assertTrue(Double.parseDouble(currentPrice.split(" ")[0].replaceAll(",", "."))
                    <= Double.parseDouble(price.replaceAll(",", ".")));
        }
    }

    public void screenSizeValidation(List<String> list, String minScreenSize, String maxScreenSize){
        for (String currentScreenSize : list){
            Assert.assertTrue(Integer.parseInt(currentScreenSize.substring(0, 2)) >= Integer.parseInt(minScreenSize)
                    && Integer.parseInt(currentScreenSize.substring(0, 2)) <= Integer.parseInt(maxScreenSize));
        }
    }
    public void resolutionValidation(List<String> list, String resolution){
        for (String currentResolution : list){
            Assert.assertTrue(currentResolution.contains(resolution));
        }
    }
}
