package steam.pages;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import framework.BasePage;
import framework.PropertyManager;
import framework.elements.Button;
import framework.elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class ActionPage extends BasePage {

    public static String comparableSaleValue;

    @Override
    public void isRightPageOpenedAssertion(String currentTitle) {
        Assert.assertEquals(driver.getTitle(), currentTitle);
    }

    public void lookingForBiggestSale(){
        int maxSaleInd = 0;
        int maxSale = 0;
        List<WebElement> recommendedSpecialsList = driver.findElements(By.xpath("//div[@class='contenthub_specials_grid_cell']//div[@class='discount_pct']"));
        for (int i = 0; i < recommendedSpecialsList.size(); i++){
            if(Integer.parseInt(recommendedSpecialsList.get(i).getText().replace("%", "")) <= maxSale){
                maxSale = Integer.parseInt(recommendedSpecialsList.get(i).getText().replace("%", ""));
                maxSaleInd = i;
            }
        }
        comparableSaleValue = recommendedSpecialsList.get(maxSaleInd).getText();
        recommendedSpecialsList.get(maxSaleInd).click();
    }

    public void ageCheck(){
        if (driver.getTitle().contains("Save")){
            PropertyManager propertyManager = new PropertyManager();
            Dropdown inputAgeDropdown = new Dropdown(By.xpath("//select[@name='ageYear']"));
            inputAgeDropdown.select("1999");
            Button openPageBtnXpath = new Button(By.xpath(String.format("//span[contains(text(), '%s')]",
                    propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "check_age_button"))));
            openPageBtnXpath.click();
        }
    }


}
