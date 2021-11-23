package steam.main_menu;

import framework.elements.Dropdown;
import framework.elements.Label;
import org.openqa.selenium.By;

public class MainMenu {

    public Label mainLabel(String mainLabelText) {
        Label mainNavLabel = new Label(By.xpath(String.format("//a[@class='pulldown_desktop'][contains(text(), '%s')]", mainLabelText)));
        return mainNavLabel;
    }

    public Dropdown subsectionChoice(String subsectionText){
        Dropdown subsectionDropdown = new Dropdown(By.xpath(String.format("//a[@class='popup_menu_item'][contains(text(),'%s')]", subsectionText)));
        return subsectionDropdown;
    }

    public void mainMenuNavigation(Label mainLabel, Dropdown subSectionDropdown){
        mainLabel.moveTo();
        subSectionDropdown.click();
    }
}
