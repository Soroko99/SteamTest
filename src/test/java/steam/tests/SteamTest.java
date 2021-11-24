package steam.tests;

import framework.BaseTest;
import framework.PropertyManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steam.main_menu.MainMenu;
import steam.pages.*;

public class SteamTest extends BaseTest {

    @BeforeTest
    @Override
    public void trashClean() {
        SteamDownload steamUninstall = new SteamDownload();
        steamUninstall.deleteSteamBeforeTest(steamUninstall.isFilePresent());
    }

    @Test
    public void start() {
        PropertyManager propertyManager = new PropertyManager();

        HomePage homePage = new HomePage();
        homePage.chooseLanguage(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "language"));
        homePage.isRightPageOpenedAssertion(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "home_title"));

        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenuNavigation(mainMenu.mainLabel(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "main_page_top_navigation")),
                mainMenu.subsectionChoice(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "main_page_top_nav_subsection")));

        ActionPage actionPage = new ActionPage();
        actionPage.isRightPageOpenedAssertion(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "action_title"));
        actionPage.lookingForBiggestSale();

        CheckAge checkAge = new CheckAge();
        if (checkAge.isAgeCheckPageOpened()) {
            checkAge.ageCheckActions(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "input_age"),
                    propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath,"check_age_open_button_page"));
        }

        GamePage gamePage = new GamePage();
        gamePage.isRightPageOpenedAssertion(gamePage.getCurrentSale());
        gamePage.installSteamBtnClick(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "install_steam"));

        SteamDownload steamInstall = new SteamDownload();
        steamInstall.isRightPageOpenedAssertion(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "steam_title"));
        steamInstall.downloadSteam(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "download_steam"));
    }
}
