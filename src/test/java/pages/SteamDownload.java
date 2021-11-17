package pages;

import framework.PropertyManager;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;

public class SteamDownload extends BasePage{

    private String downloadSteamBtnXpath = "//a[text()='%s']";
    PropertyManager propertyManager = new PropertyManager();

    @Override
    public void isRightPageOpenedAssertion() {
        Assert.assertEquals(driver.getTitle(), propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "steam_title"));
    }

    public void waitForDownload() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
        try{
            wait.until((ExpectedCondition<Boolean>) (ExpectedCondition<Boolean>) (x) -> {
                try {
                    if (js.executeScript("arguments[0].document.readyState") == "complete")
                        return true;

                } catch (Exception e) {
                    return false;
                }
                return true;
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void downloadSteam(){
        String downloadSteamText = propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "download_steam");
        Button downloadSteamBtn = new Button(By.xpath(String.format(downloadSteamBtnXpath, downloadSteamText)));
        downloadSteamBtn.click();
    }

    public Boolean isDownloadSuccess(){

        waitForDownload();
        File steamFile = new File(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "steam_save_dir")
                + (propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "app_name")));
        boolean bool = false;
        if (steamFile.exists()){
            bool = true;
        }
        return bool;
    }

    public void deleteSteamAfterTest(){
        File steamFile = new File(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "steam_save_dir")
                + (propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "app_name")));
        if(isDownloadSuccess()){
            steamFile.delete();
        }
    }
}
