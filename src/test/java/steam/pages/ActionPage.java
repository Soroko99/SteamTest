package steam.pages;

import framework.BasePage;
import framework.elements.ElementsList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class ActionPage extends BasePage {

    public static String comparableSaleValue;

    ElementsList recommendedSpecialsList = new ElementsList(By.xpath("//div[@class='contenthub_specials_grid_cell']//div[@class='discount_pct']"));

    @Override
    public void isRightPageOpenedAssertion(String currentTitle) {
        Assert.assertEquals(driver.getTitle(), currentTitle);
    }

    public void lookingForBiggestSale(){
        int maxSaleInd = 0;
        int maxSale = 0;
        for (int i = 0; i < recommendedSpecialsList.getElementList().size(); i++){
            if(Integer.parseInt(recommendedSpecialsList.getElementList().get(i).getText().replace("%", "")) <= maxSale){
                maxSale = Integer.parseInt(recommendedSpecialsList.getElementList().get(i).getText().replace("%", ""));
                maxSaleInd = i;
            }
        }
        comparableSaleValue = recommendedSpecialsList.getElementList().get(maxSaleInd).getText();
        recommendedSpecialsList.getElementList().get(maxSaleInd).click();
    }
}
