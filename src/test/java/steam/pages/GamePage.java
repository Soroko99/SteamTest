package steam.pages;

import framework.BasePage;
import framework.PropertyManager;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.testng.Assert;


public class GamePage extends BasePage {

    PropertyManager propertyManager = new PropertyManager();

    public String getCurrentSale(){
        return driver.findElement(By.xpath("//div[@class='discount_pct']")).getText();
    }

    @Override
    public void isRightPageOpenedAssertion(String currentSale) {
        Assert.assertEquals(ActionPage.comparableSaleValue, currentSale);
    }

    public void installSteamBtnClick(){
        String installSteamBtnText = propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "install_steam");
        Button installSteamBtn = new Button(By.xpath((String.format("//a[contains(text(), '%s')]", installSteamBtnText))));
        installSteamBtn.click();
    }
}
