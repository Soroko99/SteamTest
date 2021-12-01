package steam.main_menu;

import framework.elements.Dropdown;
import framework.elements.Label;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainMenu {

    String mainNavLabelXpath = "//a[@class='pulldown_desktop'][contains(text(), '%s')]";
    String subSectionLabelXpath = "//a[@class='popup_menu_item'][contains(text(),'%s')]";

    public Label mainLabel(String mainLabelText) {
        Label mainNavLabel = new Label(By.xpath(String.format(mainNavLabelXpath, mainLabelText)));
        return mainNavLabel;
    }

    public Dropdown subsectionChoice(String subsectionText){
        Dropdown subsectionDropdown = new Dropdown(By.xpath(String.format(subSectionLabelXpath, subsectionText)));
        return subsectionDropdown;
    }

    @Step("Go to Action page")
    public void mainMenuNavigation(Label mainLabel, Dropdown subSectionDropdown){
        mainLabel.moveTo();
        subSectionDropdown.click();
    }
}
