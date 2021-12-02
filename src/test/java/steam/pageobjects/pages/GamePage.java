package steam.pageobjects.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextBox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class GamePage extends BasePage {

    TextBox currentSaleTextBox = new TextBox(By.xpath("//div[@class='discount_pct']"));

    public String getCurrentSale(){
        return currentSaleTextBox.getText();
    }

    @Override
    public void isRightPageOpenedAssertion(String currentSale) {
        Assert.assertEquals(ActionPage.comparableSaleValue, currentSale);
    }

    @Step("Go to install steam page")
    public void installSteamBtnClick(String installSteamText){
        Button installSteamBtn = new Button(By.xpath((String.format("//a[contains(text(), '%s')]", installSteamText))));
        installSteamBtn.click();
    }
}
