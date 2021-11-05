package framework;

import framework.elements.BaseElement;
import framework.elements.Button;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import pages.base.BasePage;
import pages.steam_home.SteamHomePage;

import java.io.IOException;

import static pages.steam_home.SteamHomePage.langProperties;

//"//div[@id='language_dropdown']"
public class TestClass extends Browser{
    String languageBtnXpath = "//span[@id='language_pulldown']";
    String selectLanguageBtnXpath = "//a[@class='popup_menu_item tight']";
    //div[@id='language_dropdown']//a[contains(text(),'')]

    @Test
    public void start() throws IOException, InterruptedException {
        Button button = new Button(driver, (JavascriptExecutor) driver);
        SteamHomePage steamHomePage = new SteamHomePage(driver);
        BaseElement baseElement = new BaseElement(driver);
        PropertyManager propertyManager = new PropertyManager();

        driver.get("https://store.steampowered.com/");
        button.click(button.searchElement(languageBtnXpath));
        steamHomePage.searchLanguage(button.findElementsList(selectLanguageBtnXpath));
        steamHomePage.mainMenuHover(propertyManager.getExactProperty(langProperties, "main_page_top_navigation"));
        steamHomePage.mainMenuClick(propertyManager.getExactProperty(langProperties, "main_page_top_nav_subsection"));

//        button.click(driver.findElement(By.xpath("//a[contains(text(), 'English')]")));
        Thread.sleep(2000);
    }
}
