import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class TVPage {
    String s = "\u00a0";

    public String getManufacturerChoice(String name){
        return String.format("//input[@value='%s']//ancestor::label", name);
    }
    public String getPrice(String name)
    {
        return String.format("//input[contains(@placeholder, '%s')]", name);
    }

    public String getResolution(String name)
    {
        return String.format("//span[contains(text(), '%s')]/parent::label", name);
    }

    public String getScreenSize(String name)
    {
        return String.format("//select[contains(@data-bind, 'value: facet.value.%s')]", name);
    }

    public List<String> generatingTvList(WebDriver driver, String commonXpath){
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='schema-product']"));
        List<String> paramsList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            paramsList.add(list.get(i).findElements(By.xpath(String.format("%s", commonXpath))).get(i).getText());
        }
        return paramsList;
    }



}
