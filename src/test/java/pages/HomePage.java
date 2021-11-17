package pages;

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

public class HomePage extends BasePage{
    public static String currentLanguagePropertyPath;
    private String subsectionBtnXpath = "//a[@class='popup_menu_item'][contains(text(),'%s')]";
    private String mainMenuXpath = "//a[@class='pulldown_desktop'][contains(text(), '%s')]";
    private String changeLanguageBtnXpath = "//a[@class='popup_menu_item tight'][contains(text(), '%s')]";
    private String openLanguageListBtnXpath = "//span[@id='language_pulldown']";
    private String languageBtnXpath = "//a[@class='popup_menu_item tight'][contains(text(), '%s')]";
    PropertyManager propertyManager = new PropertyManager();

    public boolean checkIfLanguageExist(String currentTestLanguage){
        boolean bool = false;
        List<WebElement> languageList = driver.findElements(By.xpath(String.format(languageBtnXpath, currentTestLanguage)));
        for(WebElement element : languageList){
            if (element.getText().startsWith(currentTestLanguage)){
                bool = true;
                break;
            }
        }
        return bool;
    }

    public String getLanguage() {
        Button openLanguageListBtn = new Button(By.xpath(openLanguageListBtnXpath));
        openLanguageListBtn.click();
        String currentTestLanguage = propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "language");
        Dropdown selectLanguageDropdown = new Dropdown(By.xpath(String.format(changeLanguageBtnXpath, currentTestLanguage)));
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
    public void isRightPageOpenedAssertion() {
        waitForPageIsLoaded();
        Assert.assertEquals(driver.getTitle(), propertyManager.getExactProperty(currentLanguagePropertyPath, "home_title"));
    }

    public void mainLabelNavigation() {
        Actions actions = new Actions(driver);
        String mainLabelText = propertyManager.getExactProperty(currentLanguagePropertyPath, "main_page_top_navigation");
        WebElement mainLabelElement = driver.findElement(By.xpath(String.format(mainMenuXpath, mainLabelText)));
        actions.moveToElement(mainLabelElement).build().perform();
    }

    public void subsectionChoice(){
        String subsectionText = propertyManager.getExactProperty(currentLanguagePropertyPath, "main_page_top_nav_subsection");
        Dropdown subsectionDropdown = new Dropdown(By.xpath(String.format(subsectionBtnXpath, subsectionText)));
        subsectionDropdown.click();
    }

}




