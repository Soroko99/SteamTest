package steam.pageobjects.pages;

import framework.BasePage;
import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ActionPage extends BasePage {

    public static String comparableSaleValue;
    Label recommendedSpecials = new Label(By.xpath("//div[@class='contenthub_specials_grid_cell']//div[@class='discount_pct']"));

    @Override
    public void isRightPageOpenedAssertion(String currentTitle) {
        Assert.assertEquals(getTitle(), currentTitle);
    }

    @Step("Choose the biggest profit")
    public void lookingForBiggestSale(){
        int maxSaleInd = 0;
        int maxSale = 0;
        for (int i = 0; i < recommendedSpecials.getElementList().size(); i++){
            if(Integer.parseInt(recommendedSpecials.getElementList().get(i).getText().replace("%", "")) <= maxSale){
                maxSale = Integer.parseInt(recommendedSpecials.getElementList().get(i).getText().replace("%", ""));
                maxSaleInd = i;
            }
        }
        comparableSaleValue = recommendedSpecials.getElementList().get(maxSaleInd).getText();
        recommendedSpecials.getElementList().get(maxSaleInd).click();
    }
}
