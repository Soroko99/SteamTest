package pages.steam_home;

import framework.PropertyManager;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

import java.util.List;

public class SteamHomePage extends BasePage {
    protected String topNavBarXpath = "//div[@class='store_nav']";
    PropertyManager propertyManager = new PropertyManager();
    String engLangPropertyPath = "src/test/resources/locale_properties/eng_properties.properties";
    String ruLangPropertyPath = "src/test/resources/locale_properties/ru_properties.properties";
    String langDropdownXpath = "//div[@class='popup_body popup_menu']";
    protected String langXpath = "//div[@id='language_dropdown']//a";
    public static String langProperties;
    String languageBtnXpath = "//span[@id='language_pulldown']";
    private String recommendedSpecialsXpath = "//div[@class='contenthub_specials_grid_cell']";
    private String saleXpath = "//div[@class='discount_pct']";

    Button button = new Button(driver, (JavascriptExecutor) driver);

    public SteamHomePage(WebDriver driver) {
        super(driver);
    }


    public String searchLanguage(List<WebElement> list) {
        for (WebElement element : list) {
            if (element.getText().startsWith("Русский") && propertyManager.getExactProperty(seleniumProperty, "language").equals("Русский")) {
                langProperties = ruLangPropertyPath;
                button.click(element);
                break;
            }
            else if (element.getText().startsWith("English") && propertyManager.getExactProperty(seleniumProperty, ""))
            else {
                langProperties = engLangPropertyPath;
            }
        }
        if (langProperties.equals(engLangPropertyPath)) button.click(button.searchElement(languageBtnXpath));
        return langProperties;
    }

    public void mainMenuHover(String text){
        button.hover(button.searchElement(String.format("//a[@class='pulldown_desktop'][contains(text(),'%s')]", text)));
    }

    public void mainMenuClick(String text){
        button.click(button.searchElement(String.format("//a[@class='popup_menu_item'][contains(text(), '%s')]", text)));
    }

    public List<WebElement> generateRecommendedSpecialList(){
        return button.findElementsList(recommendedSpecialsXpath);
    }}

//    public WebElement searchingBiggestSale(List<WebElement> list){
//        double biggestSale = 0;
//        int biggestSaleInd = 0;
//        for (int i = 0; i < list.size(); i++){
//            System.out.println();
//        }
//    }
//}




//    public void checkTheLanguage(){
//        button.waitElementIsVisible(button.searchElement("//div[@class='popup_body popup_menu']"));
//
//    }
//    }

