package steam.pageobjects.pages;

import framework.BasePage;
import framework.Browser;
import framework.BrowserFactory;
import framework.PropertyManager;
import framework.elements.Button;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;

public class SteamDownload extends BasePage {

    PropertyManager propertyManager = new PropertyManager();
    File steamFile = new File(System.getProperty("user.dir") + propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "steam_save_dir")
            + (propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "app_name")));

    @Override
    public void isRightPageOpenedAssertion(String currentValue) {
        Assert.assertEquals(getTitle(), currentValue);
    }

    public void waitForDownload() {
        switch (BrowserFactory.browser) {
            case "chrome": {
                Browser currentBrowser = new Browser();
                currentBrowser.get("chrome://downloads");
                waitForProgress();
            }
            case "firefox": {
                waitForSize();
            }
        }
    }

    public boolean fileLoading(){
        JavascriptExecutor downloadWindowExecutor = (JavascriptExecutor) driver;
        double percentage = 0;
        while (percentage != 100 && !isFilePresent())
        {
            percentage = (long) downloadWindowExecutor.executeScript("return document.querySelector('downloads-manager').shadowRoot.querySelector('#downloadsList downloads-item').shadowRoot.querySelector('#progress').value");
        }
        return true;
    }

    public boolean firefoxWaiting(){
        while (FileUtils.sizeOf(steamFile) != Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "file_size")))
        {
            return false;
        }
        return true;
    }

    public void waitForSize(){
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
        try {
            wait.until((ExpectedCondition<Boolean>) (ExpectedCondition<Boolean>) (x) ->  {
                boolean bool = false;
                try {
                    if (firefoxWaiting())
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

    public void waitForProgress(){
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
        try {
            wait.until((ExpectedCondition<Boolean>) (ExpectedCondition<Boolean>) (x) ->  {
                    boolean bool = false;
                    try {
                        if (fileLoading() && isFilePresent())
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

    @Step("Click download button")
    public void downloadSteam(String downloadSteamBtnText){
        Button downloadSteamBtn = new Button(By.xpath(String.format("//a[text()='%s']", downloadSteamBtnText)));
        downloadSteamBtn.click();
        waitForDownload();
    }

    public boolean isFilePresent(){
        return steamFile.exists();
    }

    public void deleteSteamBeforeTest(boolean bool){
        if(bool){
            steamFile.delete();
        }
    }
}
