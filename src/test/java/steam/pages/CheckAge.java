package steam.pages;

import framework.PropertyManager;
import framework.elements.Button;
import framework.elements.Dropdown;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CheckAge {
    PropertyManager propertyManager = new PropertyManager();
    TextBox checkAgeTextBox = new TextBox(By.xpath("//div[@class='agegate_birthday_desc']"));
    Dropdown inputAgeDropdown = new Dropdown(By.xpath("//select[@name='ageYear']"));
    Button openPageBtnXpath = new Button(By.xpath(String.format("//span[contains(text(), '%s')]", propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "check_age_open_button_page"))));

    public boolean isAgeCheckPageOpened(){
        try {
            return checkAgeTextBox.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void ageCheckActions(String input_age){
            inputAgeDropdown.select(input_age);
            openPageBtnXpath.click();
    }
}
