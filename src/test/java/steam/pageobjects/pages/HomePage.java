package steam.pageobjects.pages;

import framework.BasePage;
import framework.PropertyManager;
import framework.elements.Button;
import framework.elements.Dropdown;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.util.HashMap;

public class HomePage extends BasePage {

    PropertyManager propertyManager = new PropertyManager();
    public static String currentLanguagePropertyPath;
    HashMap<String, String> langMap = new HashMap<>();
    Button openLanguageListBtn = new Button(By.xpath("//span[@id='language_pulldown']"));
    Dropdown languageList = new Dropdown(By.xpath("//a[@class='popup_menu_item tight']"));

    public HashMap<String,String> langList(){
        langMap.put("Русский", "src/test/resources/locale_properties/ru_propertes.properties");
        langMap.put("English", "src/test/resources/locale_properties/eng_properties.properties");
        return langMap;
    }

    @Step("Language choice")
    public void chooseLanguage(String lang){
        Dropdown selectLanguageDropdown = new Dropdown(By.xpath(String.format("//a[@class='popup_menu_item tight'][contains(text(), '%s')]", lang)));
        openLanguageListBtn.click();
        if (searchChosenLanguage(lang) && isLanguageProvided(lang))
        {
            currentLanguagePropertyPath = langList().get(lang);
            selectLanguageDropdown.click();
        }
        else {
            currentLanguagePropertyPath = langList().get(lang);
            openLanguageListBtn.click();
        }
    }

    public boolean isLanguageProvided(String lang){
        return langList().containsKey(lang);
    }

    public boolean searchChosenLanguage(String lang){
            boolean bool = false;
            for(WebElement element : languageList.getElementList()){
                if (element.getText().startsWith(lang)){
                    bool = true;
                    break;
                }
            }
        return bool;
        }

    @Override
    public void isRightPageOpenedAssertion(String currentTitle) {
        waitUntilExpectedConditions(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format("//a[@class='pulldown_desktop'][contains(text(), '%s')]",
                propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "main_page_top_navigation")))));
        Assert.assertEquals(getTitle(), currentTitle);
    }


}




