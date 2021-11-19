package steam.pages;

import framework.BasePage;
import framework.PropertyManager;
import framework.elements.Button;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.security.Key;
import java.util.Set;

public class SteamDownload extends BasePage {

    PropertyManager propertyManager = new PropertyManager();

    @Override
    public void isRightPageOpenedAssertion(String currentValue) {
        Assert.assertEquals(driver.getTitle(), currentValue);
    }

    public void waitForDownload() {
        //enteringDownloads();
        //driver.get("chrome://downloads");
        enteringDownloads();

        //enteringDownloads();
//        HomePage homePage = new HomePage();
//        homePage.waitForPageIsLoaded();
        waitPercentage();

    }

    public void enteringDownloads(){
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("j").build().perform();
        actions.keyUp(Keys.CONTROL);
    }

    public boolean waitForLoad(){
        JavascriptExecutor downloadWindowExecutor = (JavascriptExecutor) driver;
        double percentage = 0;
        System.out.println(percentage);
        while (percentage != 100 && !isFilePresent())
        {
            percentage = (Long) downloadWindowExecutor.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#progress').value");
            System.out.println(percentage);
        }
        return true;
    }

    public boolean isFilePresent(){
        boolean bool = false;
        File file = new File(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "steam_save_dir")
                + (propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "app_name")));
        if (file.exists()) bool = true;
        return bool;
    }

    public void waitPercentage(){
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
        try {
            wait.until((ExpectedCondition<Boolean>) (ExpectedCondition<Boolean>) (x) ->  {
                    boolean bool = false;
                    try {
                        if (waitForLoad() && isFilePresent())
                            bool = true;
                    } catch (Exception e) {
                        bool = false;
                    }
                    return bool;
            });
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void downloadSteam(){
        String downloadSteamText = propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "download_steam");
        Button downloadSteamBtn = new Button(By.xpath(String.format("//a[text()='%s']", downloadSteamText)));
        downloadSteamBtn.click();
        waitForDownload();
    }

    public Boolean isDownloadSuccess(){
        File steamFile = new File(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "steam_save_dir")
                + (propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "app_name")));
        boolean bool = false;
        if (steamFile.exists()){
            bool = true;
        }
        else System.out.println("((((((");
        return bool;
    }

    public void deleteSteamAfterTest(){
        isDownloadSuccess();
        File steamFile = new File(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "steam_save_dir")
                + (propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "app_name")));
        if(steamFile.exists()){
            steamFile.delete();
        }
    }
}
