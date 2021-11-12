package pages;

import framework.PropertyManager;
import framework.elements.Button;
import framework.elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class ActionPage extends BasePage {

    public static String comparableSaleValue;
    private String recommendedSpecialsListXpath = "//div[@class='contenthub_specials_grid_cell']//div[@class='discount_pct']";
    private String checkAgeUrl = "https://store.steampowered.com/agecheck/app/8870/";
    private String checkAgeDropdownXpath = "//select[@name='ageYear']";
    private String dropDownInputValue = "1999";
    private String checkAgeOpenPageBtnXpath = "//span[contains(text(), 'Открыть страницу')]";

    @Override
    public void isRightPageOpenedAssertion() {
        PropertyManager propertyManager = new PropertyManager();
        Assert.assertEquals(driver.getTitle(), propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "action_title"));
    }

    public void lookingForBiggestSale(){
        int maxSaleInd = 0;
        int maxSale = 0;
        List<WebElement> recommendedSpecialsList = driver.findElements(By.xpath(recommendedSpecialsListXpath));
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
        if (driver.getCurrentUrl().equals(checkAgeUrl)){
            Dropdown inputAgeDropdown = new Dropdown(By.xpath(checkAgeDropdownXpath));
            inputAgeDropdown.select(dropDownInputValue);
            Button openPageBtnXpath = new Button(By.xpath(checkAgeOpenPageBtnXpath));
            openPageBtnXpath.click();
        }
    }


}
