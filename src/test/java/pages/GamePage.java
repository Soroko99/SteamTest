package pages;

import framework.PropertyManager;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.testng.Assert;


public class GamePage extends BasePage {

    private String installSteamBtnXpath = "//a[contains(text(), '%s')]";
    private String currentGameSaleValueXpath = "//div[@class='discount_pct']";
    PropertyManager propertyManager = new PropertyManager();

    @Override
    public void isRightPageOpenedAssertion() {
        String currentSale = driver.findElement(By.xpath(currentGameSaleValueXpath)).getText();
        Assert.assertEquals(ActionPage.comparableSaleValue, currentSale);
    }

    public void installSteamBtnClick(){
        String installSteamBtnText = propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "install_steam");
        Button installSteamBtn = new Button(By.xpath((String.format(installSteamBtnXpath, installSteamBtnText))));
        installSteamBtn.click();
    }
}
