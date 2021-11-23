package steam.pages;

import framework.elements.Button;
import framework.elements.Dropdown;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CheckAge {
    TextBox checkAgeTextBox = new TextBox(By.xpath("//div[@class='agegate_birthday_desc']"));
    Dropdown inputAgeDropdown = new Dropdown(By.xpath("//select[@name='ageYear']"));

    public boolean isAgeCheckPageOpened() {
        try {
            return checkAgeTextBox.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void ageCheckActions(String input_age, String openPageBtnText) {
        inputAgeDropdown.select(input_age);
        Button openPageBtnXpath = new Button(By.xpath(String.format("//span[contains(text(), '%s')]", openPageBtnText)));
        openPageBtnXpath.click();
    }
}
