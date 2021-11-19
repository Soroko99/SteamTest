package steam.pages;

import framework.BasePage;
import framework.PropertyManager;
import framework.elements.Button;
import framework.elements.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

import static framework.PropertyManager.engLangPropertyPath;
import static framework.PropertyManager.ruLangPropertyPath;

public class HomePage extends BasePage {
    public static String currentLanguagePropertyPath;
    PropertyManager propertyManager = new PropertyManager();


    public boolean checkIfLanguageExist(String currentTestLanguage){
        boolean bool = false;
        List<WebElement> languageList = driver.findElements(By.xpath(String.format("//a[@class='popup_menu_item tight'][contains(text(), '%s')]", currentTestLanguage)));
        for(WebElement element : languageList){
            if (element.getText().startsWith(currentTestLanguage)){
                bool = true;
                break;
            }
        }
        return bool;
    }

    public String getLanguage() {
        Button openLanguageListBtn = new Button(By.xpath("//span[@id='language_pulldown']"));
        openLanguageListBtn.click();
        String currentTestLanguage = propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "language");
        Dropdown selectLanguageDropdown = new Dropdown(By.xpath(String.format("//a[@class='popup_menu_item tight'][contains(text(), '%s')]", currentTestLanguage)));
        if (currentTestLanguage.equals("Русский")){
            if (checkIfLanguageExist(currentTestLanguage)) {
                currentLanguagePropertyPath = ruLangPropertyPath;
                selectLanguageDropdown.click();
            }
            if (!checkIfLanguageExist(currentTestLanguage)) {
                currentLanguagePropertyPath = ruLangPropertyPath;
                openLanguageListBtn.click();
            }
        }
        if (currentTestLanguage.equals("English")){
            if (checkIfLanguageExist(currentTestLanguage)) {
                currentLanguagePropertyPath = engLangPropertyPath;
                selectLanguageDropdown.click();
            }
            if (!checkIfLanguageExist(currentTestLanguage)) {
                currentLanguagePropertyPath = engLangPropertyPath;
                openLanguageListBtn.click();
            }
        }
        return currentLanguagePropertyPath;
    }

    public void waitForPageIsLoaded(){
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

    @Override
    public void isRightPageOpenedAssertion(String currentTitle) {
        waitForPageIsLoaded();
        Assert.assertEquals(driver.getTitle(), currentTitle);
    }

    public void mainLabelNavigation(String mainLabelText) {
        Actions actions = new Actions(driver);
        WebElement mainLabelElement = driver.findElement(By.xpath(String.format("//a[@class='pulldown_desktop'][contains(text(), '%s')]", mainLabelText)));
        actions.moveToElement(mainLabelElement).build().perform();
    }

    public void subsectionChoice(String subsectionText){
        Dropdown subsectionDropdown = new Dropdown(By.xpath(String.format("//a[@class='popup_menu_item'][contains(text(),'%s')]", subsectionText)));
        subsectionDropdown.click();
    }

}




